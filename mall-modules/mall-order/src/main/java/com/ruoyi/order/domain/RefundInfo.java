package com.ruoyi.order.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 退款信息对象 oms_refund_info
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public class RefundInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 退款的订单 */
    @Excel(name = "退款的订单")
    private Long orderReturnId;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal refund;

    /** 退款交易流水号 */
    @Excel(name = "退款交易流水号")
    private String refundSn;

    /** 退款状态 */
    @Excel(name = "退款状态")
    private Integer refundStatus;

    /** 退款渠道[1-支付宝，2-微信，3-银联，4-汇款] */
    @Excel(name = "退款渠道[1-支付宝，2-微信，3-银联，4-汇款]")
    private Integer refundChannel;

    /** $column.columnComment */
    @Excel(name = "退款渠道[1-支付宝，2-微信，3-银联，4-汇款]")
    private String refundContent;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderReturnId(Long orderReturnId) 
    {
        this.orderReturnId = orderReturnId;
    }

    public Long getOrderReturnId() 
    {
        return orderReturnId;
    }
    public void setRefund(BigDecimal refund) 
    {
        this.refund = refund;
    }

    public BigDecimal getRefund() 
    {
        return refund;
    }
    public void setRefundSn(String refundSn) 
    {
        this.refundSn = refundSn;
    }

    public String getRefundSn() 
    {
        return refundSn;
    }
    public void setRefundStatus(Integer refundStatus) 
    {
        this.refundStatus = refundStatus;
    }

    public Integer getRefundStatus() 
    {
        return refundStatus;
    }
    public void setRefundChannel(Integer refundChannel) 
    {
        this.refundChannel = refundChannel;
    }

    public Integer getRefundChannel() 
    {
        return refundChannel;
    }
    public void setRefundContent(String refundContent) 
    {
        this.refundContent = refundContent;
    }

    public String getRefundContent() 
    {
        return refundContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderReturnId", getOrderReturnId())
            .append("refund", getRefund())
            .append("refundSn", getRefundSn())
            .append("refundStatus", getRefundStatus())
            .append("refundChannel", getRefundChannel())
            .append("refundContent", getRefundContent())
            .toString();
    }
}
