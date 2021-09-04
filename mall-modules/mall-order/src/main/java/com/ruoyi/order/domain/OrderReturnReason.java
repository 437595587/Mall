package com.ruoyi.order.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 退货原因对象 oms_order_return_reason
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public class OrderReturnReason extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 退货原因名 */
    @Excel(name = "退货原因名")
    private String name;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
