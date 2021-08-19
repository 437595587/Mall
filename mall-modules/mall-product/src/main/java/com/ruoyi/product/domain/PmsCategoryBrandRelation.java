package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 品牌分类关联对象 pms_category_brand_relation
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsCategoryBrandRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 品牌id */
    @Excel(name = "品牌id")
    private Long brandId;

    /** 分类id */
    @Excel(name = "分类id")
    private Long catelogId;

    /** $column.columnComment */
    @Excel(name = "分类id")
    private String brandName;

    /** $column.columnComment */
    @Excel(name = "分类id")
    private String catelogName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setCatelogId(Long catelogId) 
    {
        this.catelogId = catelogId;
    }

    public Long getCatelogId() 
    {
        return catelogId;
    }
    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    public String getBrandName() 
    {
        return brandName;
    }
    public void setCatelogName(String catelogName) 
    {
        this.catelogName = catelogName;
    }

    public String getCatelogName() 
    {
        return catelogName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("brandId", getBrandId())
            .append("catelogId", getCatelogId())
            .append("brandName", getBrandName())
            .append("catelogName", getCatelogName())
            .toString();
    }
}
