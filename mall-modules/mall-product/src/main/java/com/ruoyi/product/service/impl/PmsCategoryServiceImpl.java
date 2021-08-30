package com.ruoyi.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.product.domain.PmsCategory;
import com.ruoyi.product.domain.vo.Catelog2Vo;
import com.ruoyi.product.mapper.PmsCategoryMapper;
import com.ruoyi.product.service.IPmsCategoryService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 分类管理Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-18
 */
@Service
public class PmsCategoryServiceImpl implements IPmsCategoryService
{
    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 查询分类管理
     *
     * @param catId 分类管理主键
     * @return 分类管理
     */
    @Override
    public PmsCategory selectPmsCategoryByCatId(Long catId)
    {
        return pmsCategoryMapper.selectPmsCategoryByCatId(catId);
    }

    /**
     * 查询分类管理列表
     *
     * @param pmsCategory 分类管理
     * @return 分类管理
     */
    @Override
    public List<PmsCategory> selectPmsCategoryList(PmsCategory pmsCategory)
    {
        List<PmsCategory> pmsCategories = pmsCategoryMapper.selectPmsCategoryList(pmsCategory);
        //过滤掉showStatus为0的数据
        List<PmsCategory> filterList = pmsCategories.stream().filter(item -> item.getShowStatus() == 0).collect(Collectors.toList());
        for (int i = 0; i < pmsCategories.size(); i++) {
            PmsCategory item = pmsCategories.get(i);
            for (PmsCategory filter : filterList) {
                if (filter.getCatId().equals(item.getCatId())) {
                    pmsCategories.remove(i);
                    i--;
                    removeFilterParent(item, pmsCategories);
                }
            }
        }
        return pmsCategories;
    }

    private void removeFilterParent(PmsCategory parent, List<PmsCategory> pmsCategories) {
        for (int i = 0; i < pmsCategories.size(); i++) {
            PmsCategory item = pmsCategories.get(i);
            if (item.getParentCid().equals(parent.getCatId())) {
                pmsCategories.remove(i);
                i--;
                removeFilterParent(item, pmsCategories);
            }
        }
    }

    /**
     * 新增分类管理
     *
     * @param pmsCategory 分类管理
     * @return 结果
     */
    @Override
    public int insertPmsCategory(PmsCategory pmsCategory)
    {
        return pmsCategoryMapper.insertPmsCategory(pmsCategory);
    }

    /**
     * 修改分类管理
     * @CacheEvict 失效模式
     * @param pmsCategory 分类管理
     * @return 结果
     */
//    @Caching(evict = {
//            @CacheEvict(value = "category",key = "'selectLevel1Categorys'"),
//            @CacheEvict(value = "category",key = "'selectCatalogJson'"),
//    })
    // 存储同一类型的数据，都可以指定成同一个分区 value
    @CacheEvict(value = "category", allEntries = true)
    //双写模式
//    @CachePut 将返回值再保存到缓存中
    @Override
    public int updatePmsCategory(PmsCategory pmsCategory)
    {
        return pmsCategoryMapper.updatePmsCategory(pmsCategory);
    }

    /**
     * 批量删除分类管理
     *
     * @param catIds 需要删除的分类管理主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryByCatIds(Long[] catIds)
    {
        return pmsCategoryMapper.deletePmsCategoryByCatIds(catIds);
    }

    /**
     * 删除分类管理信息
     *
     * @param catId 分类管理主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryByCatId(Long catId)
    {
        return pmsCategoryMapper.deletePmsCategoryByCatId(catId);
    }

    /**
     * key默认自动生成：缓存的名字::SimpleKey []    自主生成的key值
     * 缓存的value值，默认使用jdk序列化机制，将序列化的数据存到redis
     * 默认ttl时间 -1
     *
     * 自定义：
     *  (1) 指定生成的缓存使用的key   key属性指定，接受一个SpEL
     *  (2) 指定缓存的数据的存活时间 配置文件中修改 spring.cache.redis.time-to-live=3600000
     *  (3) 将数据保存为json格式
     *
     * @return
     */
    @Cacheable(value = {"category"},key = "#root.method.name", sync = true)
    @Override
    public List<PmsCategory> selectLevel1Categorys() {
        return pmsCategoryMapper.selectLevel1Categorys();
    }

    @Cacheable(value = "category",key = "#root.methodName")
    @Override
    public Map<String, List<Catelog2Vo>> selectCatalogJson() {
        List<PmsCategory> selectList = pmsCategoryMapper.selectPmsCategoryList(null);

        List<PmsCategory> level1Categorys = getParentCid(selectList, 0L);
        Map<String, List<Catelog2Vo>> collect = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1. 每一个一级分类的二级分类
            List<PmsCategory> categories = getParentCid(selectList, v.getCatId());
            List<Catelog2Vo> catelog2Vos = null;
            if (categories != null) {
                catelog2Vos = categories.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //找二级分类的三级分类封装成vo
                    List<PmsCategory> level3Catelog = getParentCid(selectList, l2.getCatId());
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catelog3Vo> collect1 = level3Catelog.stream().map(l3 -> {
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect1);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));
        return collect;
    }

    public Map<String, List<Catelog2Vo>> selectCatalogJson2() {
        String catalogJson = redisTemplate.opsForValue().get("catalogJson");
        if (StringUtils.isEmpty(catalogJson)) {
            Map<String, List<Catelog2Vo>> catalogJsonFromDb = selectCatalogJsonFromDbWithRedissonLock();
            return catalogJsonFromDb;
        }
        return JSON.parseObject(catalogJson, new TypeReference<Map<String, List<Catelog2Vo>>>() {});
    }

    /**
     * 缓存数据一致性
     * 1)、双写模式
     * 2)、失效模式
     * @return
     */
    public Map<String, List<Catelog2Vo>> selectCatalogJsonFromDbWithRedissonLock() {
        //1. 锁的名字 锁的粒度
        RLock lock = redissonClient.getLock("catalogJson-lock");
        lock.lock();
        Map<String, List<Catelog2Vo>> dataFormDb;
        try {
            dataFormDb = selectDataFromDb();
        } finally {
            lock.unlock();
        }
        return dataFormDb;
    }

    //从数据库查询并封装分类数据
    public Map<String, List<Catelog2Vo>> selectCatalogJsonFromDbWithRedisLock() {
        //1. 占分布式锁。
        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 300, TimeUnit.SECONDS);
        if (lock != null && lock) {
            //加锁成功
            //设置过期时间
//            redisTemplate.expire("lock", 30, TimeUnit.SECONDS);
            Map<String, List<Catelog2Vo>> dataFormDb;
            try {
                dataFormDb = selectDataFromDb();
            } finally {
                //lua脚本解锁
                String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
                        "then\n" +
                        "    return redis.call(\"del\",KEYS[1])\n" +
                        "else\n" +
                        "    return 0\n" +
                        "end";
                //原子删锁
                redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList("lock"), uuid);
            }
//            redisTemplate.delete("lock");
//            String lockValue = redisTemplate.opsForValue().get("lock");
//            if (uuid.equals(lockValue)) {
//                redisTemplate.delete("lock");
//            }
            return dataFormDb;
        } else {
            //加锁失败
            //休眠100ms重试
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return selectCatalogJsonFromDbWithRedisLock();
        }
    }

    private Map<String, List<Catelog2Vo>> selectDataFromDb() {
        String catalogJson = redisTemplate.opsForValue().get("catalogJson");
        if (StringUtils.isNotEmpty(catalogJson)) {
            return JSON.parseObject(catalogJson, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
        }
        List<PmsCategory> selectList = pmsCategoryMapper.selectPmsCategoryList(null);

        List<PmsCategory> level1Categorys = getParentCid(selectList, 0L);
        Map<String, List<Catelog2Vo>> collect = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1. 每一个一级分类的二级分类
            List<PmsCategory> categories = getParentCid(selectList, v.getCatId());
            List<Catelog2Vo> catelog2Vos = null;
            if (categories != null) {
                catelog2Vos = categories.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //找二级分类的三级分类封装成vo
                    List<PmsCategory> level3Catelog = getParentCid(selectList, l2.getCatId());
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catelog3Vo> collect1 = level3Catelog.stream().map(l3 -> {
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect1);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));
        String s = JSON.toJSONString(collect);
        redisTemplate.opsForValue().set("catalogJson", s, 1, TimeUnit.DAYS);
        return collect;
    }

    //从数据库查询并封装分类数据
    public Map<String, List<Catelog2Vo>> selectCatalogJsonFromDbWithLocalLock() {
        synchronized (this) {
            return selectDataFromDb();
        }
    }

    private List<PmsCategory> getParentCid(List<PmsCategory> selectList, Long parentCid) {
        return selectList.stream().filter(item -> item.getParentCid().equals(parentCid)).collect(Collectors.toList());
    }

}
