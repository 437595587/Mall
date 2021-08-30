package com.ruoyi.coupon.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 商品阶梯价格对象 sms_sku_ladder
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public class SkuLadder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** spu_id */
    @Excel(name = "spu_id")
    private Long skuId;

    /** 满几件 */
    @Excel(name = "满几件")
    private Long fullCount;

    /** 打几折 */
    @Excel(name = "打几折")
    private BigDecimal discount;

    /** 折后价 */
    @Excel(name = "折后价")
    private BigDecimal price;

    /** 是否叠加其他优惠[0-不可叠加，1-可叠加] */
    @Excel(name = "是否叠加其他优惠[0-不可叠加，1-可叠加]")
    private Integer addOther;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }
    public void setFullCount(Long fullCount) 
    {
        this.fullCount = fullCount;
    }

    public Long getFullCount() 
    {
        return fullCount;
    }
    public void setDiscount(BigDecimal discount) 
    {
        this.discount = discount;
    }

    public BigDecimal getDiscount() 
    {
        return discount;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setAddOther(Integer addOther) 
    {
        this.addOther = addOther;
    }

    public Integer getAddOther() 
    {
        return addOther;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("skuId", getSkuId())
            .append("fullCount", getFullCount())
            .append("discount", getDiscount())
            .append("price", getPrice())
            .append("addOther", getAddOther())
            .toString();
    }
}
