package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 商品属性对象 pms_attr
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsAttr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 属性id */
    private Long attrId;

    /** 属性名 */
    @Excel(name = "属性名")
    private String attrName;

    /** 是否需要检索[0-不需要，1-需要] */
    @Excel(name = "是否需要检索[0-不需要，1-需要]")
    private Integer searchType;

    /** 值类型[0-为单个值，1-可以选择多个值] */
    @Excel(name = "值类型[0-为单个值，1-可以选择多个值]")
    private Integer valueType;

    /** 属性图标 */
    @Excel(name = "属性图标")
    private String icon;

    /** 可选值列表[用逗号分隔] */
    @Excel(name = "可选值列表[用逗号分隔]")
    private String valueSelect;

    /** 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性] */
    @Excel(name = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;

    /** 启用状态[0 - 禁用，1 - 启用] */
    @Excel(name = "启用状态[0 - 禁用，1 - 启用]")
    private Long enable;

    /** 所属分类 */
    @Excel(name = "所属分类")
    private Long catelogId;

    /** 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整 */
    @Excel(name = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Integer showDesc;

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
    public void setSearchType(Integer searchType) 
    {
        this.searchType = searchType;
    }

    public Integer getSearchType() 
    {
        return searchType;
    }
    public void setValueType(Integer valueType) 
    {
        this.valueType = valueType;
    }

    public Integer getValueType() 
    {
        return valueType;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setValueSelect(String valueSelect) 
    {
        this.valueSelect = valueSelect;
    }

    public String getValueSelect() 
    {
        return valueSelect;
    }
    public void setAttrType(Integer attrType) 
    {
        this.attrType = attrType;
    }

    public Integer getAttrType() 
    {
        return attrType;
    }
    public void setEnable(Long enable) 
    {
        this.enable = enable;
    }

    public Long getEnable() 
    {
        return enable;
    }
    public void setCatelogId(Long catelogId) 
    {
        this.catelogId = catelogId;
    }

    public Long getCatelogId() 
    {
        return catelogId;
    }
    public void setShowDesc(Integer showDesc) 
    {
        this.showDesc = showDesc;
    }

    public Integer getShowDesc() 
    {
        return showDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attrId", getAttrId())
            .append("attrName", getAttrName())
            .append("searchType", getSearchType())
            .append("valueType", getValueType())
            .append("icon", getIcon())
            .append("valueSelect", getValueSelect())
            .append("attrType", getAttrType())
            .append("enable", getEnable())
            .append("catelogId", getCatelogId())
            .append("showDesc", getShowDesc())
            .toString();
    }
}
