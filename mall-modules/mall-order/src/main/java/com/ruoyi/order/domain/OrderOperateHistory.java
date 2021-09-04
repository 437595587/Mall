package com.ruoyi.order.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 订单操作历史记录对象 oms_order_operate_history
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public class OrderOperateHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 操作人[用户；系统；后台管理员] */
    @Excel(name = "操作人[用户；系统；后台管理员]")
    private String operateMan;

    /** 订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】 */
    @Excel(name = "订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】")
    private Integer orderStatus;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setOperateMan(String operateMan) 
    {
        this.operateMan = operateMan;
    }

    public String getOperateMan() 
    {
        return operateMan;
    }
    public void setOrderStatus(Integer orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() 
    {
        return orderStatus;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("operateMan", getOperateMan())
            .append("createTime", getCreateTime())
            .append("orderStatus", getOrderStatus())
            .append("note", getNote())
            .toString();
    }
}
