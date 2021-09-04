package com.ruoyi.order.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 订单项信息对象 oms_order_item
 *
 * @author xuxing
 * @date 2021-08-31
 */
public class OrderItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** order_id */
    @Excel(name = "order_id")
    private Long orderId;

    /** order_sn */
    @Excel(name = "order_sn")
    private String orderSn;

    /** spu_id */
    @Excel(name = "spu_id")
    private Long spuId;

    /** spu_name */
    @Excel(name = "spu_name")
    private String spuName;

    /** spu_pic */
    @Excel(name = "spu_pic")
    private String spuPic;

    /** 品牌 */
    @Excel(name = "品牌")
    private String spuBrand;

    /** 商品分类id */
    @Excel(name = "商品分类id")
    private Long categoryId;

    /** 商品sku编号 */
    @Excel(name = "商品sku编号")
    private Long skuId;

    /** 商品sku名字 */
    @Excel(name = "商品sku名字")
    private String skuName;

    /** 商品sku图片 */
    @Excel(name = "商品sku图片")
    private String skuPic;

    /** 商品sku价格 */
    @Excel(name = "商品sku价格")
    private BigDecimal skuPrice;

    /** 商品购买的数量 */
    @Excel(name = "商品购买的数量")
    private Long skuQuantity;

    /** 商品销售属性组合（JSON） */
    @Excel(name = "商品销售属性组合", readConverterExp = "J=SON")
    private String skuAttrsVals;

    /** 商品促销分解金额 */
    @Excel(name = "商品促销分解金额")
    private BigDecimal promotionAmount;

    /** 优惠券优惠分解金额 */
    @Excel(name = "优惠券优惠分解金额")
    private BigDecimal couponAmount;

    /** 积分优惠分解金额 */
    @Excel(name = "积分优惠分解金额")
    private BigDecimal integrationAmount;

    /** 该商品经过优惠后的分解金额 */
    @Excel(name = "该商品经过优惠后的分解金额")
    private BigDecimal realAmount;

    /** 赠送积分 */
    @Excel(name = "赠送积分")
    private Long giftIntegration;

    /** 赠送成长值 */
    @Excel(name = "赠送成长值")
    private Long giftGrowth;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setOrderSn(String orderSn)
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn()
    {
        return orderSn;
    }
    public void setSpuId(Long spuId)
    {
        this.spuId = spuId;
    }

    public Long getSpuId()
    {
        return spuId;
    }
    public void setSpuName(String spuName)
    {
        this.spuName = spuName;
    }

    public String getSpuName()
    {
        return spuName;
    }
    public void setSpuPic(String spuPic)
    {
        this.spuPic = spuPic;
    }

    public String getSpuPic()
    {
        return spuPic;
    }
    public void setSpuBrand(String spuBrand)
    {
        this.spuBrand = spuBrand;
    }

    public String getSpuBrand()
    {
        return spuBrand;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setSkuId(Long skuId)
    {
        this.skuId = skuId;
    }

    public Long getSkuId()
    {
        return skuId;
    }
    public void setSkuName(String skuName)
    {
        this.skuName = skuName;
    }

    public String getSkuName()
    {
        return skuName;
    }
    public void setSkuPic(String skuPic)
    {
        this.skuPic = skuPic;
    }

    public String getSkuPic()
    {
        return skuPic;
    }
    public void setSkuPrice(BigDecimal skuPrice)
    {
        this.skuPrice = skuPrice;
    }

    public BigDecimal getSkuPrice()
    {
        return skuPrice;
    }
    public void setSkuQuantity(Long skuQuantity)
    {
        this.skuQuantity = skuQuantity;
    }

    public Long getSkuQuantity()
    {
        return skuQuantity;
    }
    public void setSkuAttrsVals(String skuAttrsVals)
    {
        this.skuAttrsVals = skuAttrsVals;
    }

    public String getSkuAttrsVals()
    {
        return skuAttrsVals;
    }
    public void setPromotionAmount(BigDecimal promotionAmount)
    {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getPromotionAmount()
    {
        return promotionAmount;
    }
    public void setCouponAmount(BigDecimal couponAmount)
    {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getCouponAmount()
    {
        return couponAmount;
    }
    public void setIntegrationAmount(BigDecimal integrationAmount)
    {
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getIntegrationAmount()
    {
        return integrationAmount;
    }
    public void setRealAmount(BigDecimal realAmount)
    {
        this.realAmount = realAmount;
    }

    public BigDecimal getRealAmount()
    {
        return realAmount;
    }
    public void setGiftIntegration(Long giftIntegration)
    {
        this.giftIntegration = giftIntegration;
    }

    public Long getGiftIntegration()
    {
        return giftIntegration;
    }
    public void setGiftGrowth(Long giftGrowth)
    {
        this.giftGrowth = giftGrowth;
    }

    public Long getGiftGrowth()
    {
        return giftGrowth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderSn", getOrderSn())
            .append("spuId", getSpuId())
            .append("spuName", getSpuName())
            .append("spuPic", getSpuPic())
            .append("spuBrand", getSpuBrand())
            .append("categoryId", getCategoryId())
            .append("skuId", getSkuId())
            .append("skuName", getSkuName())
            .append("skuPic", getSkuPic())
            .append("skuPrice", getSkuPrice())
            .append("skuQuantity", getSkuQuantity())
            .append("skuAttrsVals", getSkuAttrsVals())
            .append("promotionAmount", getPromotionAmount())
            .append("couponAmount", getCouponAmount())
            .append("integrationAmount", getIntegrationAmount())
            .append("realAmount", getRealAmount())
            .append("giftIntegration", getGiftIntegration())
            .append("giftGrowth", getGiftGrowth())
            .toString();
    }
}
