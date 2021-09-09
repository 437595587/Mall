package com.ruoyi.seckill.schedule;

import com.ruoyi.seckill.service.ISeckillService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 秒杀商品的定时上架
 * 每天晚上3点，上架最近三天需要秒杀的商品
 */
@Component
public class SeckillSkuScheduled {

    @Autowired
    private ISeckillService seckillService;

    @Autowired
    private RedissonClient redissonClient;

    private final String upload_lock = "seckill:upload:lock";

    @Scheduled(cron = "0 * * * * ?")
    public void uploadSeckillSkuLatest3Days() {
        //重复上架无需处理
        RLock lock = redissonClient.getLock(upload_lock);
        lock.lock(10, TimeUnit.SECONDS);
        //保证操作的原子性，防止其他服务也进行操作
        try {
            seckillService.uploadSeckillSkuLatest3Days();
        } finally {
            lock.unlock();
        }
    }
}
