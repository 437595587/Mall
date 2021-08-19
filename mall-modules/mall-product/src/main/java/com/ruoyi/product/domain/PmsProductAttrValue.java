package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * spu属性值对象 pms_product_attr_value
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsProductAttrValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品id */
    @Excel(name = "商品id")
    private Long spuId;

    /** 属性id */
    @Excel(name = "属性id")
    private Long attrId;

    /** 属性名 */
    @Excel(name = "属性名")
    private String attrName;

    /** 属性值 */
    @Excel(name = "属性值")
    private String attrValue;

    /** 顺序 */
    @Excel(name = "顺序")
    private Long attrSort;

    /** 快速展示【是否展示在介绍上；0-否 1-是】 */
    @Excel(name = "快速展示【是否展示在介绍上；0-否 1-是】")
    private Integer quickShow;

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
    public void setAttrId(Long attrId) 
    {
        this.attrId = attrId;
    }

    public Long getAttrId() 
    {
        return attrId;
    }
    public void setAttrName(String attrName) 
    {
        this.attrName = attrName;
    }

    public String getAttrName() 
    {
        return attrName;
    }
    public void setAttrValue(String attrValue) 
    {
        this.attrValue = attrValue;
    }

    public String getAttrValue() 
    {
        return attrValue;
    }
    public void setAttrSort(Long attrSort) 
    {
        this.attrSort = attrSort;
    }

    public Long getAttrSort() 
    {
        return attrSort;
    }
    public void setQuickShow(Integer quickShow) 
    {
        this.quickShow = quickShow;
    }

    public Integer getQuickShow() 
    {
        return quickShow;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("spuId", getSpuId())
            .append("attrId", getAttrId())
            .append("attrName", getAttrName())
            .append("attrValue", getAttrValue())
            .append("attrSort", getAttrSort())
            .append("quickShow", getQuickShow())
            .toString();
    }
}
