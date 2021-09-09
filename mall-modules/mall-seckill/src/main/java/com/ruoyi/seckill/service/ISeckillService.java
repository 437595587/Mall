package com.ruoyi.seckill.service;

import com.ruoyi.seckill.domain.to.SeckillSkuRedisTo;

import java.util.List;

public interface ISeckillService {
    void uploadSeckillSkuLatest3Days();

    List<SeckillSkuRedisTo> selectCurrentSeckillSkus();

    SeckillSkuRedisTo selectSkuSeckillInfo(Long skuId);

    String kill(String killId, String key, Integer num);
}
