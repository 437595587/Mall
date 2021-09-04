package com.ruoyi.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退货申请对象 oms_order_return_apply
 *
 * @author xuxing
 * @date 2021-08-31
 */
public class OrderReturnApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** order_id */
    @Excel(name = "order_id")
    private Long orderId;

    /** 退货商品id */
    @Excel(name = "退货商品id")
    private Long skuId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderSn;

    /** 会员用户名 */
    @Excel(name = "会员用户名")
    private String memberUsername;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal returnAmount;

    /** 退货人姓名 */
    @Excel(name = "退货人姓名")
    private String returnName;

    /** 退货人电话 */
    @Excel(name = "退货人电话")
    private String returnPhone;

    /** 申请状态[0-&gt;待处理；1-&gt;退货中；2-&gt;已完成；3-&gt;已拒绝] */
    @Excel(name = "申请状态[0-&gt;待处理；1-&gt;退货中；2-&gt;已完成；3-&gt;已拒绝]")
    private Integer status;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String skuImg;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String skuName;

    /** 商品品牌 */
    @Excel(name = "商品品牌")
    private String skuBrand;

    /** 商品销售属性(JSON) */
    @Excel(name = "商品销售属性(JSON)")
    private String skuAttrsVals;

    /** 退货数量 */
    @Excel(name = "退货数量")
    private Long skuCount;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private BigDecimal skuPrice;

    /** 商品实际支付单价 */
    @Excel(name = "商品实际支付单价")
    private BigDecimal skuRealPrice;

    /** 原因 */
    @Excel(name = "原因")
    private String reason;

    /** 描述 */
    @Excel(name = "描述")
    private String description述;

    /** 凭证图片，以逗号隔开 */
    @Excel(name = "凭证图片，以逗号隔开")
    private String descPics;

    /** 处理备注 */
    @Excel(name = "处理备注")
    private String handleNote;

    /** 处理人员 */
    @Excel(name = "处理人员")
    private String handleMan;

    /** 收货人 */
    @Excel(name = "收货人")
    private String receiveMan;

    /** 收货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "收货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;

    /** 收货备注 */
    @Excel(name = "收货备注")
    private String receiveNote;

    /** 收货电话 */
    @Excel(name = "收货电话")
    private String receivePhone;

    /** 公司收货地址 */
    @Excel(name = "公司收货地址")
    private String companyAddress;

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
    public void setSkuId(Long skuId)
    {
        this.skuId = skuId;
    }

    public Long getSkuId()
    {
        return skuId;
    }
    public void setOrderSn(String orderSn)
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn()
    {
        return orderSn;
    }
    public void setMemberUsername(String memberUsername)
    {
        this.memberUsername = memberUsername;
    }

    public String getMemberUsername()
    {
        return memberUsername;
    }
    public void setReturnAmount(BigDecimal returnAmount)
    {
        this.returnAmount = returnAmount;
    }

    public BigDecimal getReturnAmount()
    {
        return returnAmount;
    }
    public void setReturnName(String returnName)
    {
        this.returnName = returnName;
    }

    public String getReturnName()
    {
        return returnName;
    }
    public void setReturnPhone(String returnPhone)
    {
        this.returnPhone = returnPhone;
    }

    public String getReturnPhone()
    {
        return returnPhone;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setHandleTime(Date handleTime)
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime()
    {
        return handleTime;
    }
    public void setSkuImg(String skuImg)
    {
        this.skuImg = skuImg;
    }

    public String getSkuImg()
    {
        return skuImg;
    }
    public void setSkuName(String skuName)
    {
        this.skuName = skuName;
    }

    public String getSkuName()
    {
        return skuName;
    }
    public void setSkuBrand(String skuBrand)
    {
        this.skuBrand = skuBrand;
    }

    public String getSkuBrand()
    {
        return skuBrand;
    }
    public void setSkuAttrsVals(String skuAttrsVals)
    {
        this.skuAttrsVals = skuAttrsVals;
    }

    public String getSkuAttrsVals()
    {
        return skuAttrsVals;
    }
    public void setSkuCount(Long skuCount)
    {
        this.skuCount = skuCount;
    }

    public Long getSkuCount()
    {
        return skuCount;
    }
    public void setSkuPrice(BigDecimal skuPrice)
    {
        this.skuPrice = skuPrice;
    }

    public BigDecimal getSkuPrice()
    {
        return skuPrice;
    }
    public void setSkuRealPrice(BigDecimal skuRealPrice)
    {
        this.skuRealPrice = skuRealPrice;
    }

    public BigDecimal getSkuRealPrice()
    {
        return skuRealPrice;
    }
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
    public void setDescription述(String description述)
    {
        this.description述 = description述;
    }

    public String getDescription述()
    {
        return description述;
    }
    public void setDescPics(String descPics)
    {
        this.descPics = descPics;
    }

    public String getDescPics()
    {
        return descPics;
    }
    public void setHandleNote(String handleNote)
    {
        this.handleNote = handleNote;
    }

    public String getHandleNote()
    {
        return handleNote;
    }
    public void setHandleMan(String handleMan)
    {
        this.handleMan = handleMan;
    }

    public String getHandleMan()
    {
        return handleMan;
    }
    public void setReceiveMan(String receiveMan)
    {
        this.receiveMan = receiveMan;
    }

    public String getReceiveMan()
    {
        return receiveMan;
    }
    public void setReceiveTime(Date receiveTime)
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime()
    {
        return receiveTime;
    }
    public void setReceiveNote(String receiveNote)
    {
        this.receiveNote = receiveNote;
    }

    public String getReceiveNote()
    {
        return receiveNote;
    }
    public void setReceivePhone(String receivePhone)
    {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone()
    {
        return receivePhone;
    }
    public void setCompanyAddress(String companyAddress)
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress()
    {
        return companyAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("skuId", getSkuId())
            .append("orderSn", getOrderSn())
            .append("createTime", getCreateTime())
            .append("memberUsername", getMemberUsername())
            .append("returnAmount", getReturnAmount())
            .append("returnName", getReturnName())
            .append("returnPhone", getReturnPhone())
            .append("status", getStatus())
            .append("handleTime", getHandleTime())
            .append("skuImg", getSkuImg())
            .append("skuName", getSkuName())
            .append("skuBrand", getSkuBrand())
            .append("skuAttrsVals", getSkuAttrsVals())
            .append("skuCount", getSkuCount())
            .append("skuPrice", getSkuPrice())
            .append("skuRealPrice", getSkuRealPrice())
            .append("reason", getReason())
            .append("description述", getDescription述())
            .append("descPics", getDescPics())
            .append("handleNote", getHandleNote())
            .append("handleMan", getHandleMan())
            .append("receiveMan", getReceiveMan())
            .append("receiveTime", getReceiveTime())
            .append("receiveNote", getReceiveNote())
            .append("receivePhone", getReceivePhone())
            .append("companyAddress", getCompanyAddress())
            .toString();
    }
}
