package com.ruoyi.coupon.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品spu积分设置对象 sms_spu_bounds
 *
 * @author xuxing
 * @date 2021-08-23
 */
public class SpuBounds extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long spuId;

    /** 成长积分 */
    @Excel(name = "成长积分")
    private BigDecimal growBounds;

    /** 购物积分 */
    @Excel(name = "购物积分")
    private BigDecimal buyBounds;

    /** 优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】] */
    @Excel(name = "优惠生效情况[1111", readConverterExp = "四=个状态位，从右到左")
    private Integer work;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSpuId(Long spuId)
    {
        this.spuId = spuId;
    }

    public Long getSpuId()
    {
        return spuId;
    }
    public void setGrowBounds(BigDecimal growBounds)
    {
        this.growBounds = growBounds;
    }

    public BigDecimal getGrowBounds()
    {
        return growBounds;
    }
    public void setBuyBounds(BigDecimal buyBounds)
    {
        this.buyBounds = buyBounds;
    }

    public BigDecimal getBuyBounds()
    {
        return buyBounds;
    }
    public void setWork(Integer work)
    {
        this.work = work;
    }

    public Integer getWork()
    {
        return work;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("spuId", getSpuId())
            .append("growBounds", getGrowBounds())
            .append("buyBounds", getBuyBounds())
            .append("work", getWork())
            .toString();
    }
}
