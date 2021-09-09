package com.ruoyi.seckill.domain.to;

import com.ruoyi.mall.api.vo.PmsSkuInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeckillSkuRedisTo {
    /** id */
    private Long id;

    /** 活动id */
    private Long promotionId;

    /** 活动场次id */
    private Long promotionSessionId;

    /** 商品id */
    private Long skuId;

    /** 秒杀价格 */
    private BigDecimal seckillPrice;

    /** 秒杀总量 */
    private Long seckillCount;

    /** 每人限购数量 */
    private Long seckillLimit;

    /** 排序 */
    private Long seckillSort;

    //sku详细信息
    private PmsSkuInfo skuInfo;

    //秒杀的开始时间
    private Long startTime;

    //秒杀的结束时间
    private Long endTime;

    //随机码
    private String randomCode;
}
