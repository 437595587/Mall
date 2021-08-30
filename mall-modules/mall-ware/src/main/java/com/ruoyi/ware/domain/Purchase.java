package com.ruoyi.ware.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 采购单对象 wms_purchase
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public class Purchase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购单id */
    private Long id;

    /** 采购人id */
    @Excel(name = "采购人id")
    private Long assigneeId;

    /** 采购人名 */
    @Excel(name = "采购人名")
    private String assigneeName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 优先级 */
    @Excel(name = "优先级")
    private Long priority;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 仓库id */
    @Excel(name = "仓库id")
    private Long wareId;

    /** 总金额 */
    @Excel(name = "总金额")
    private Long amount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAssigneeId(Long assigneeId) 
    {
        this.assigneeId = assigneeId;
    }

    public Long getAssigneeId() 
    {
        return assigneeId;
    }
    public void setAssigneeName(String assigneeName) 
    {
        this.assigneeName = assigneeName;
    }

    public String getAssigneeName() 
    {
        return assigneeName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setPriority(Long priority) 
    {
        this.priority = priority;
    }

    public Long getPriority() 
    {
        return priority;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setWareId(Long wareId) 
    {
        this.wareId = wareId;
    }

    public Long getWareId() 
    {
        return wareId;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assigneeId", getAssigneeId())
            .append("assigneeName", getAssigneeName())
            .append("phone", getPhone())
            .append("priority", getPriority())
            .append("status", getStatus())
            .append("wareId", getWareId())
            .append("amount", getAmount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
