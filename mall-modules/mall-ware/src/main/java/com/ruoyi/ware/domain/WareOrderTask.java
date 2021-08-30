package com.ruoyi.ware.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 库存工作单对象 wms_ware_order_task
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public class WareOrderTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** order_id */
    @Excel(name = "order_id")
    private Long orderId;

    /** order_sn */
    @Excel(name = "order_sn")
    private String orderSn;

    /** 收货人 */
    @Excel(name = "收货人")
    private String consignee;

    /** 收货人电话 */
    @Excel(name = "收货人电话")
    private String consigneeTel;

    /** 配送地址 */
    @Excel(name = "配送地址")
    private String deliveryAddress;

    /** 订单备注 */
    @Excel(name = "订单备注")
    private String orderComment;

    /** 付款方式【 1:在线付款 2:货到付款】 */
    @Excel(name = "付款方式【 1:在线付款 2:货到付款】")
    private Integer paymentWay;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Integer taskStatus;

    /** 订单描述 */
    @Excel(name = "订单描述")
    private String orderBody;

    /** 物流单号 */
    @Excel(name = "物流单号")
    private String trackingNo;

    /** 仓库id */
    @Excel(name = "仓库id")
    private Long wareId;

    /** 工作单备注 */
    @Excel(name = "工作单备注")
    private String taskComment;

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
    public void setOrderSn(String orderSn) 
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn() 
    {
        return orderSn;
    }
    public void setConsignee(String consignee) 
    {
        this.consignee = consignee;
    }

    public String getConsignee() 
    {
        return consignee;
    }
    public void setConsigneeTel(String consigneeTel) 
    {
        this.consigneeTel = consigneeTel;
    }

    public String getConsigneeTel() 
    {
        return consigneeTel;
    }
    public void setDeliveryAddress(String deliveryAddress) 
    {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() 
    {
        return deliveryAddress;
    }
    public void setOrderComment(String orderComment) 
    {
        this.orderComment = orderComment;
    }

    public String getOrderComment() 
    {
        return orderComment;
    }
    public void setPaymentWay(Integer paymentWay) 
    {
        this.paymentWay = paymentWay;
    }

    public Integer getPaymentWay() 
    {
        return paymentWay;
    }
    public void setTaskStatus(Integer taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskStatus() 
    {
        return taskStatus;
    }
    public void setOrderBody(String orderBody) 
    {
        this.orderBody = orderBody;
    }

    public String getOrderBody() 
    {
        return orderBody;
    }
    public void setTrackingNo(String trackingNo) 
    {
        this.trackingNo = trackingNo;
    }

    public String getTrackingNo() 
    {
        return trackingNo;
    }
    public void setWareId(Long wareId) 
    {
        this.wareId = wareId;
    }

    public Long getWareId() 
    {
        return wareId;
    }
    public void setTaskComment(String taskComment) 
    {
        this.taskComment = taskComment;
    }

    public String getTaskComment() 
    {
        return taskComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderSn", getOrderSn())
            .append("consignee", getConsignee())
            .append("consigneeTel", getConsigneeTel())
            .append("deliveryAddress", getDeliveryAddress())
            .append("orderComment", getOrderComment())
            .append("paymentWay", getPaymentWay())
            .append("taskStatus", getTaskStatus())
            .append("orderBody", getOrderBody())
            .append("trackingNo", getTrackingNo())
            .append("createTime", getCreateTime())
            .append("wareId", getWareId())
            .append("taskComment", getTaskComment())
            .toString();
    }
}
