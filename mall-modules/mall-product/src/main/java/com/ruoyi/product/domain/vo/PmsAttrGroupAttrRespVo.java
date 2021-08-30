package com.ruoyi.product.domain.vo;

import com.ruoyi.product.domain.PmsAttr;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class PmsAttrGroupAttrRespVo {
    private static final long serialVersionUID = 1L;

    /** 分组id */
    private Long attrGroupId;

    /** 组名 */
    private String attrGroupName;

    /** 排序 */
    private Long sort;

    /** 描述 */
    private String descript;

    /** 组图标 */
    private String icon;

    /** 所属分类id */
    private Long catelogId;

    /** 属性分组的关联属性 */
    private List<PmsAttr> attrs;

    public List<PmsAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<PmsAttr> attrs) {
        this.attrs = attrs;
    }

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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("attrGroupId", getAttrGroupId())
                .append("attrGroupName", getAttrGroupName())
                .append("sort", getSort())
                .append("descript", getDescript())
                .append("icon", getIcon())
                .append("catelogId", getCatelogId())
                .append("attrs", getAttrs())
                .toString();
    }
}
