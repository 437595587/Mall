package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 属性&属性分组关联对象 pms_attr_attrgroup_relation
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsAttrAttrgroupRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 属性id */
    @Excel(name = "属性id")
    private Long attrId;

    /** 属性分组id */
    @Excel(name = "属性分组id")
    private Long attrGroupId;

    /** 属性组内排序 */
    @Excel(name = "属性组内排序")
    private Long attrSort;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAttrId(Long attrId) 
    {
        this.attrId = attrId;
    }

    public Long getAttrId() 
    {
        return attrId;
    }
    public void setAttrGroupId(Long attrGroupId) 
    {
        this.attrGroupId = attrGroupId;
    }

    public Long getAttrGroupId() 
    {
        return attrGroupId;
    }
    public void setAttrSort(Long attrSort) 
    {
        this.attrSort = attrSort;
    }

    public Long getAttrSort() 
    {
        return attrSort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("attrId", getAttrId())
            .append("attrGroupId", getAttrGroupId())
            .append("attrSort", getAttrSort())
            .toString();
    }
}
