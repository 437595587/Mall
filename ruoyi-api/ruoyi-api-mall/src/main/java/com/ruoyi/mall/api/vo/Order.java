package com.ruoyi.mall.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单对象 oms_order
 *
 * @author xuxing
 * @date 2021-08-31
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** member_id */
    private Long memberId;

    /** 订单号 */
    private String orderSn;

    /** 使用的优惠券 */
    private Long couponId;

    /** 用户名 */
    private String memberUsername;

    /** 订单总额 */
    private BigDecimal totalAmount;

    /** 应付总额 */
    private BigDecimal payAmount;

    /** 运费金额 */
    private BigDecimal freightAmount;

    /** 促销优化金额（促销价、满减、阶梯价） */
    private BigDecimal promotionAmount;

    /** 积分抵扣金额 */
    private BigDecimal integrationAmount;

    /** 优惠券抵扣金额 */
    private BigDecimal couponAmount;

    /** 后台调整订单使用的折扣金额 */
    private BigDecimal discountAmount;

    /** 支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】 */
    private Integer payType;

    /** 订单来源[0->PC订单；1->app订单] */
    private Integer sourceType;

    /** 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】 */
    private Integer status;

    /** 物流公司(配送方式) */
    private String deliveryCompany;

    /** 物流单号 */
    private String deliverySn;

    /** 自动确认时间（天） */
    private Long autoConfirmDay;

    /** 可以获得的积分 */
    private Long integration;

    /** 可以获得的成长值 */
    private Long growth;

    /** 发票类型[0->不开发票；1->电子发票；2->纸质发票] */
    private Integer billType;

    /** 发票抬头 */
    private String billHeader;

    /** 发票内容 */
    private String billContent;

    /** 收票人电话 */
    private String billReceiverPhone;

    /** 收票人邮箱 */
    private String billReceiverEmail;

    /** 收货人姓名 */
    private String receiverName;

    /** 收货人电话 */
    private String receiverPhone;

    /** 收货人邮编 */
    private String receiverPostCode;

    /** 省份/直辖市 */
    private String receiverProvince;

    /** 城市 */
    private String receiverCity;

    /** 区 */
    private String receiverRegion;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String receiverDetailAddress;

    /** 订单备注 */
    private String note;

    /** 确认收货状态[0->未确认；1->已确认] */
    private Integer confirmStatus;

    /** 删除状态【0->未删除；1->已删除】 */
    private Integer deleteStatus;

    /** 下单时使用的积分 */
    private Long useIntegration;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTime;

    /** 确认收货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    /** 评价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private List<OrderItem> items;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
    }
    public void setOrderSn(String orderSn)
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn()
    {
        return orderSn;
    }
    public void setCouponId(Long couponId)
    {
        this.couponId = couponId;
    }

    public Long getCouponId()
    {
        return couponId;
    }
    public void setMemberUsername(String memberUsername)
    {
        this.memberUsername = memberUsername;
    }

    public String getMemberUsername()
    {
        return memberUsername;
    }
    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }
    public void setPayAmount(BigDecimal payAmount)
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount()
    {
        return payAmount;
    }
    public void setFreightAmount(BigDecimal freightAmount)
    {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getFreightAmount()
    {
        return freightAmount;
    }
    public void setPromotionAmount(BigDecimal promotionAmount)
    {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getPromotionAmount()
    {
        return promotionAmount;
    }
    public void setIntegrationAmount(BigDecimal integrationAmount)
    {
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getIntegrationAmount()
    {
        return integrationAmount;
    }
    public void setCouponAmount(BigDecimal couponAmount)
    {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getCouponAmount()
    {
        return couponAmount;
    }
    public void setDiscountAmount(BigDecimal discountAmount)
    {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountAmount()
    {
        return discountAmount;
    }
    public void setPayType(Integer payType)
    {
        this.payType = payType;
    }

    public Integer getPayType()
    {
        return payType;
    }
    public void setSourceType(Integer sourceType)
    {
        this.sourceType = sourceType;
    }

    public Integer getSourceType()
    {
        return sourceType;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setDeliveryCompany(String deliveryCompany)
    {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryCompany()
    {
        return deliveryCompany;
    }
    public void setDeliverySn(String deliverySn)
    {
        this.deliverySn = deliverySn;
    }

    public String getDeliverySn()
    {
        return deliverySn;
    }
    public void setAutoConfirmDay(Long autoConfirmDay)
    {
        this.autoConfirmDay = autoConfirmDay;
    }

    public Long getAutoConfirmDay()
    {
        return autoConfirmDay;
    }
    public void setIntegration(Long integration)
    {
        this.integration = integration;
    }

    public Long getIntegration()
    {
        return integration;
    }
    public void setGrowth(Long growth)
    {
        this.growth = growth;
    }

    public Long getGrowth()
    {
        return growth;
    }
    public void setBillType(Integer billType)
    {
        this.billType = billType;
    }

    public Integer getBillType()
    {
        return billType;
    }
    public void setBillHeader(String billHeader)
    {
        this.billHeader = billHeader;
    }

    public String getBillHeader()
    {
        return billHeader;
    }
    public void setBillContent(String billContent)
    {
        this.billContent = billContent;
    }

    public String getBillContent()
    {
        return billContent;
    }
    public void setBillReceiverPhone(String billReceiverPhone)
    {
        this.billReceiverPhone = billReceiverPhone;
    }

    public String getBillReceiverPhone()
    {
        return billReceiverPhone;
    }
    public void setBillReceiverEmail(String billReceiverEmail)
    {
        this.billReceiverEmail = billReceiverEmail;
    }

    public String getBillReceiverEmail()
    {
        return billReceiverEmail;
    }
    public void setReceiverName(String receiverName)
    {
        this.receiverName = receiverName;
    }

    public String getReceiverName()
    {
        return receiverName;
    }
    public void setReceiverPhone(String receiverPhone)
    {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPhone()
    {
        return receiverPhone;
    }
    public void setReceiverPostCode(String receiverPostCode)
    {
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverPostCode()
    {
        return receiverPostCode;
    }
    public void setReceiverProvince(String receiverProvince)
    {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverProvince()
    {
        return receiverProvince;
    }
    public void setReceiverCity(String receiverCity)
    {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCity()
    {
        return receiverCity;
    }
    public void setReceiverRegion(String receiverRegion)
    {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverRegion()
    {
        return receiverRegion;
    }
    public void setReceiverDetailAddress(String receiverDetailAddress)
    {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getReceiverDetailAddress()
    {
        return receiverDetailAddress;
    }
    public void setNote(String note)
    {
        this.note = note;
    }

    public String getNote()
    {
        return note;
    }
    public void setConfirmStatus(Integer confirmStatus)
    {
        this.confirmStatus = confirmStatus;
    }

    public Integer getConfirmStatus()
    {
        return confirmStatus;
    }
    public void setDeleteStatus(Integer deleteStatus)
    {
        this.deleteStatus = deleteStatus;
    }

    public Integer getDeleteStatus()
    {
        return deleteStatus;
    }
    public void setUseIntegration(Long useIntegration)
    {
        this.useIntegration = useIntegration;
    }

    public Long getUseIntegration()
    {
        return useIntegration;
    }
    public void setPaymentTime(Date paymentTime)
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime()
    {
        return paymentTime;
    }
    public void setDeliveryTime(Date deliveryTime)
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime()
    {
        return deliveryTime;
    }
    public void setReceiveTime(Date receiveTime)
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime()
    {
        return receiveTime;
    }
    public void setCommentTime(Date commentTime)
    {
        this.commentTime = commentTime;
    }

    public Date getCommentTime()
    {
        return commentTime;
    }
    public void setModifyTime(Date modifyTime)
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime()
    {
        return modifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("orderSn", getOrderSn())
            .append("couponId", getCouponId())
            .append("createTime", getCreateTime())
            .append("memberUsername", getMemberUsername())
            .append("totalAmount", getTotalAmount())
            .append("payAmount", getPayAmount())
            .append("freightAmount", getFreightAmount())
            .append("promotionAmount", getPromotionAmount())
            .append("integrationAmount", getIntegrationAmount())
            .append("couponAmount", getCouponAmount())
            .append("discountAmount", getDiscountAmount())
            .append("payType", getPayType())
            .append("sourceType", getSourceType())
            .append("status", getStatus())
            .append("deliveryCompany", getDeliveryCompany())
            .append("deliverySn", getDeliverySn())
            .append("autoConfirmDay", getAutoConfirmDay())
            .append("integration", getIntegration())
            .append("growth", getGrowth())
            .append("billType", getBillType())
            .append("billHeader", getBillHeader())
            .append("billContent", getBillContent())
            .append("billReceiverPhone", getBillReceiverPhone())
            .append("billReceiverEmail", getBillReceiverEmail())
            .append("receiverName", getReceiverName())
            .append("receiverPhone", getReceiverPhone())
            .append("receiverPostCode", getReceiverPostCode())
            .append("receiverProvince", getReceiverProvince())
            .append("receiverCity", getReceiverCity())
            .append("receiverRegion", getReceiverRegion())
            .append("receiverDetailAddress", getReceiverDetailAddress())
            .append("note", getNote())
            .append("confirmStatus", getConfirmStatus())
            .append("deleteStatus", getDeleteStatus())
            .append("useIntegration", getUseIntegration())
            .append("paymentTime", getPaymentTime())
            .append("deliveryTime", getDeliveryTime())
            .append("receiveTime", getReceiveTime())
            .append("commentTime", getCommentTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
