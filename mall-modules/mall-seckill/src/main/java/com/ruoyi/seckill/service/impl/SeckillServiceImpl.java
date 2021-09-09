package com.ruoyi.seckill.service.impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.IdUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.mall.api.RemoteCouponService;
import com.ruoyi.mall.api.RemoteProductService;
import com.ruoyi.mall.api.vo.Member;
import com.ruoyi.mall.api.vo.PmsSkuInfo;
import com.ruoyi.mall.api.vo.SeckillSession;
import com.ruoyi.mall.api.vo.SeckillSkuRelation;
import com.ruoyi.mall.common.core.to.mq.SeckillOrderTo;
import com.ruoyi.seckill.domain.to.SeckillSkuRedisTo;
import com.ruoyi.seckill.interceptor.LoginUserInterceptor;
import com.ruoyi.seckill.service.ISeckillService;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SeckillServiceImpl implements ISeckillService {

    @Autowired
    private RemoteCouponService remoteCouponService;

    @Autowired
    private RemoteProductService remoteProductService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";

    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";

    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";//商品随机码

    private static final Logger log = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Override
    public void uploadSeckillSkuLatest3Days() {
        //1、最近三天需要参与秒杀的活动
        R<List<SeckillSession>> r = remoteCouponService.getLatest3DaysSession();
        if (r.getCode() == 200) {
            List<SeckillSession> sessions = r.getData();
            if (sessions != null && sessions.size() > 0) {
                saveSessionInfos(sessions);
                saveSessionSkuInfos(sessions);
            }
        }
    }

    public List<SeckillSkuRedisTo> blockHandler(BlockException e) {
        log.error("selectCurrentSeckillSkus被限流了...");
        return null;
    }

    /**
     * blockHandler：函数在原方法被限流/降级/系统保护的时候调用
     * fallback：针对所有类型的异常
     *
     * @return 结果
     */
    @SentinelResource(value = "selectCurrentSeckillSkus", blockHandler = "blockHandler")
    @Override
    public List<SeckillSkuRedisTo> selectCurrentSeckillSkus() {
        //1、确定当前时间属于哪个秒杀场次
        long time = new Date().getTime();
        try (Entry entry = SphU.entry("seckillSkus")) {
            Collection<String> keys = redisService.keys(SESSIONS_CACHE_PREFIX + "*");
            for (String key : keys) {
                String replace = key.replace(SESSIONS_CACHE_PREFIX, "");
                String[] s = replace.split("_");
                long start = Long.parseLong(s[0]);
                long end = Long.parseLong(s[1]);
                if (time >= start && time <= end) {
                    //2、获取秒杀场次需要的所有商品信息
                    List<Object> cacheList = redisService.getCacheList(key);
                    List<SeckillSkuRedisTo> list = redisService.getMultiCacheMapValue(SKUKILL_CACHE_PREFIX, cacheList);
                    if (list != null && list.size() > 0) {
                        return list;
                    }
                    break;
                }
            }
        } catch (BlockException e) {
            log.error("资源被限流，{}", e.getMessage());
        }
        return null;
    }

    @Override
    public SeckillSkuRedisTo selectSkuSeckillInfo(Long skuId) {
        Map<String, SeckillSkuRedisTo> cacheMap = redisService.getCacheMap(SKUKILL_CACHE_PREFIX);
        if (cacheMap != null && cacheMap.size() > 0) {
            Set<Map.Entry<String, SeckillSkuRedisTo>> kvs = cacheMap.entrySet();
            for (Map.Entry<String, SeckillSkuRedisTo> kv : kvs) {
                String key = kv.getKey();
                String regx = "\\d_" + skuId;
                if (Pattern.matches(regx, key)) {
                    SeckillSkuRedisTo skuRedisTo = kv.getValue();
                    long current = new Date().getTime();
                    if (current >= skuRedisTo.getStartTime() && current <= skuRedisTo.getEndTime()) {

                    } else {
                        skuRedisTo.setRandomCode(null);
                    }
                    return skuRedisTo;
                }
            }
        }
        return null;
    }

    //TODO 上架秒杀商品的时候，每一个数据都有过期时间
    //TODO 秒杀后续流程，收获地址等信息
    @Override
    public String kill(String killId, String key, Integer num) {
        Member member = LoginUserInterceptor.loginUser.get();
        //1、获取当前秒杀商品的详细信息
        SeckillSkuRedisTo redisTo = redisService.getCacheMapValue(SKUKILL_CACHE_PREFIX, killId);
        if (redisTo == null) {
            return null;
        } else {
            //校验合法性
            Long startTime = redisTo.getStartTime();
            Long endTime = redisTo.getEndTime();
            long time = new Date().getTime();
            long ttl = endTime - startTime;
            //校验时间合法性
            if (time >= startTime && time <= endTime) {
                //校验随机码和商品id
                String randomCode = redisTo.getRandomCode();
                String skuId = redisTo.getPromotionSessionId() + "_" + redisTo.getSkuId();
                if (randomCode.equals(key) && killId.equals(skuId)) {
                    //验证购物数量
                    if (num <= redisTo.getSeckillCount()) {
                        //验证是否购买过，幂等性。 只要秒杀成功，就去占位 userId_sessionId_skuId
                        String redisKey = member.getId() + "_" + skuId;
                        //自动过期
                        Boolean aBoolean = redisService.redisTemplate.opsForValue().setIfAbsent(redisKey, num, ttl, TimeUnit.MILLISECONDS);
                        if (aBoolean != null && aBoolean) {
                            //占位成功：从来没有买过
                            RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + randomCode);

                            boolean b = semaphore.tryAcquire(num);
                            if (b) {
                                //秒杀成功
                                //快速下单，发送MQ消息
                                String orderSn = IdUtils.fastSimpleUUID();
                                SeckillOrderTo orderTo = new SeckillOrderTo();
                                orderTo.setOrderSn(orderSn);
                                orderTo.setMemberId(member.getId());
                                orderTo.setNum(num);
                                orderTo.setPromotionSessionId(redisTo.getPromotionSessionId());
                                orderTo.setSkuId(redisTo.getSkuId());
                                orderTo.setSeckillPrice(redisTo.getSeckillPrice());
                                rabbitTemplate.convertAndSend("order.event.exchange", "order.seckill.order", orderTo);
                                return orderSn;
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    private void saveSessionInfos(List<SeckillSession> sessions) {
        for (SeckillSession session : sessions) {
            String key = SESSIONS_CACHE_PREFIX + session.getStartTime().getTime() + "_" + session.getEndTime().getTime();
            List<SeckillSkuRelation> relationSkus = session.getRelationSkus();
            if (!redisService.hasKey(key)) {
                List<String> values = relationSkus.stream()
                        .map(sku -> sku.getPromotionSessionId() + "_" + sku.getSkuId())
                        .collect(Collectors.toList());
                //缓存活动信息
                redisService.setCacheList(key, values);
            }
        }
    }

    private void saveSessionSkuInfos(List<SeckillSession> sessions) {
        for (SeckillSession session : sessions) {
            List<SeckillSkuRelation> relationSkus = session.getRelationSkus();
            for (SeckillSkuRelation skuRelation : relationSkus) {
                String key = skuRelation.getPromotionSessionId() + "_" + skuRelation.getSkuId();
                if (redisService.getCacheMapValue(SKUKILL_CACHE_PREFIX, key) == null) {
                    SeckillSkuRedisTo redisTo = new SeckillSkuRedisTo();
                    //1、sku基本数据
                    R<PmsSkuInfo> r = remoteProductService.getSkuInfo(skuRelation.getSkuId());
                    if (r.getCode() == 200) {
                        PmsSkuInfo data = r.getData();
                        redisTo.setSkuInfo(data);
                    }
                    //2、sku的秒杀信息
                    BeanUtils.copyBeanProp(redisTo, skuRelation);
                    //3、设置商品的秒杀时间信息
                    redisTo.setStartTime(session.getStartTime().getTime());
                    redisTo.setEndTime(session.getEndTime().getTime());
                    //4、随机码
                    redisTo.setRandomCode(IdUtils.fastSimpleUUID());
                    //5、保存
                    redisService.setCacheMapValue(SKUKILL_CACHE_PREFIX, key, redisTo);
                    //使用库存作为分布式的信号量 限流
                    RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + redisTo.getRandomCode());
                    //商品秒杀的数量作为信号量
                    semaphore.trySetPermits(skuRelation.getSeckillCount().intValue());
                }
            }
        }
    }
}
