package com.ruoyi.product.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * sku信息对象 pms_sku_info
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsSkuInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** skuId */
    private Long skuId;

    /** spuId */
    @Excel(name = "spuId")
    private Long spuId;

    /** sku名称 */
    @Excel(name = "sku名称")
    private String skuName;

    /** sku介绍描述 */
    @Excel(name = "sku介绍描述")
    private String skuDesc;

    /** 所属分类id */
    @Excel(name = "所属分类id")
    private Long catalogId;

    /** 品牌id */
    @Excel(name = "品牌id")
    private Long brandId;

    /** 默认图片 */
    @Excel(name = "默认图片")
    private String skuDefaultImg;

    /** 标题 */
    @Excel(name = "标题")
    private String skuTitle;

    /** 副标题 */
    @Excel(name = "副标题")
    private String skuSubtitle;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 销量 */
    @Excel(name = "销量")
    private Long saleCount;

    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }
    public void setSpuId(Long spuId) 
    {
        this.spuId = spuId;
    }

    public Long getSpuId() 
    {
        return spuId;
    }
    public void setSkuName(String skuName) 
    {
        this.skuName = skuName;
    }

    public String getSkuName() 
    {
        return skuName;
    }
    public void setSkuDesc(String skuDesc) 
    {
        this.skuDesc = skuDesc;
    }

    public String getSkuDesc() 
    {
        return skuDesc;
    }
    public void setCatalogId(Long catalogId) 
    {
        this.catalogId = catalogId;
    }

    public Long getCatalogId() 
    {
        return catalogId;
    }
    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setSkuDefaultImg(String skuDefaultImg) 
    {
        this.skuDefaultImg = skuDefaultImg;
    }

    public String getSkuDefaultImg() 
    {
        return skuDefaultImg;
    }
    public void setSkuTitle(String skuTitle) 
    {
        this.skuTitle = skuTitle;
    }

    public String getSkuTitle() 
    {
        return skuTitle;
    }
    public void setSkuSubtitle(String skuSubtitle) 
    {
        this.skuSubtitle = skuSubtitle;
    }

    public String getSkuSubtitle() 
    {
        return skuSubtitle;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setSaleCount(Long saleCount) 
    {
        this.saleCount = saleCount;
    }

    public Long getSaleCount() 
    {
        return saleCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("skuId", getSkuId())
            .append("spuId", getSpuId())
            .append("skuName", getSkuName())
            .append("skuDesc", getSkuDesc())
            .append("catalogId", getCatalogId())
            .append("brandId", getBrandId())
            .append("skuDefaultImg", getSkuDefaultImg())
            .append("skuTitle", getSkuTitle())
            .append("skuSubtitle", getSkuSubtitle())
            .append("price", getPrice())
            .append("saleCount", getSaleCount())
            .toString();
    }
}
