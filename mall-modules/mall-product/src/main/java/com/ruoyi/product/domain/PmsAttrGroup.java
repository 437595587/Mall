package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 属性分组对象 pms_attr_group
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsAttrGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分组id */
    private Long attrGroupId;

    /** 组名 */
    @Excel(name = "组名")
    private String attrGroupName;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 描述 */
    @Excel(name = "描述")
    private String descript;

    /** 组图标 */
    @Excel(name = "组图标")
    private String icon;

    /** 所属分类id */
    @Excel(name = "所属分类id")
    private Long catelogId;

    public void setAttrGroupId(Long attrGroupId) 
    {
        this.attrGroupId = attrGroupId;
    }

    public Long getAttrGroupId() 
    {
        return attrGroupId;
    }
    public void setAttrGroupName(String attrGroupName) 
    {
        this.attrGroupName = attrGroupName;
    }

    public String getAttrGroupName() 
    {
        return attrGroupName;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setDescript(String descript) 
    {
        this.descript = descript;
    }

    public String getDescript() 
    {
        return descript;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setCatelogId(Long catelogId) 
    {
        this.catelogId = catelogId;
    }

    public Long getCatelogId() 
    {
        return catelogId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attrGroupId", getAttrGroupId())
            .append("attrGroupName", getAttrGroupName())
            .append("sort", getSort())
            .append("descript", getDescript())
            .append("icon", getIcon())
            .append("catelogId", getCatelogId())
            .toString();
    }
}
