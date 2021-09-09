package com.ruoyi.mall.api.vo;

import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 秒杀活动商品关联对象 sms_seckill_sku_relation
 *
 * @author xuxing
 * @date 2021-08-23
 */
public class SeckillSkuRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 活动id */
    private Long promotionId;

    /** 活动场次id */
    private Long promotionSessionId;

    /** 商品id */
    private Long skuId;

    /** 秒杀价格 */
    private Long seckillPrice;

    /** 秒杀总量 */
    private Long seckillCount;

    /** 每人限购数量 */
    private Long seckillLimit;

    /** 排序 */
    private Long seckillSort;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPromotionId(Long promotionId)
    {
        this.promotionId = promotionId;
    }

    public Long getPromotionId()
    {
        return promotionId;
    }
    public void setPromotionSessionId(Long promotionSessionId)
    {
        this.promotionSessionId = promotionSessionId;
    }

    public Long getPromotionSessionId()
    {
        return promotionSessionId;
    }
    public void setSkuId(Long skuId)
    {
        this.skuId = skuId;
    }

    public Long getSkuId()
    {
        return skuId;
    }
    public void setSeckillPrice(Long seckillPrice)
    {
        this.seckillPrice = seckillPrice;
    }

    public Long getSeckillPrice()
    {
        return seckillPrice;
    }
    public void setSeckillCount(Long seckillCount)
    {
        this.seckillCount = seckillCount;
    }

    public Long getSeckillCount()
    {
        return seckillCount;
    }
    public void setSeckillLimit(Long seckillLimit)
    {
        this.seckillLimit = seckillLimit;
    }

    public Long getSeckillLimit()
    {
        return seckillLimit;
    }
    public void setSeckillSort(Long seckillSort)
    {
        this.seckillSort = seckillSort;
    }

    public Long getSeckillSort()
    {
        return seckillSort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("promotionId", getPromotionId())
            .append("promotionSessionId", getPromotionSessionId())
            .append("skuId", getSkuId())
            .append("seckillPrice", getSeckillPrice())
            .append("seckillCount", getSeckillCount())
            .append("seckillLimit", getSeckillLimit())
            .append("seckillSort", getSeckillSort())
            .toString();
    }
}
