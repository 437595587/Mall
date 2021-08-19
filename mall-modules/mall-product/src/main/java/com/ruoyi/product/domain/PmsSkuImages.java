package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * sku图片对象 pms_sku_images
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsSkuImages extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** sku_id */
    @Excel(name = "sku_id")
    private Long skuId;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imgUrl;

    /** 排序 */
    @Excel(name = "排序")
    private Long imgSort;

    /** 默认图[0 - 不是默认图，1 - 是默认图] */
    @Excel(name = "默认图[0 - 不是默认图，1 - 是默认图]")
    private Long defaultImg;

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
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setImgSort(Long imgSort) 
    {
        this.imgSort = imgSort;
    }

    public Long getImgSort() 
    {
        return imgSort;
    }
    public void setDefaultImg(Long defaultImg) 
    {
        this.defaultImg = defaultImg;
    }

    public Long getDefaultImg() 
    {
        return defaultImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("skuId", getSkuId())
            .append("imgUrl", getImgUrl())
            .append("imgSort", getImgSort())
            .append("defaultImg", getDefaultImg())
            .toString();
    }
}
