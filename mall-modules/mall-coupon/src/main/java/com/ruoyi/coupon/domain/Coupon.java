package com.ruoyi.coupon.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 优惠券信息对象 sms_coupon
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public class Coupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券] */
    @Excel(name = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
    private Integer couponType;

    /** 优惠券图片 */
    @Excel(name = "优惠券图片")
    private String couponImg;

    /** 优惠卷名字 */
    @Excel(name = "优惠卷名字")
    private String couponName;

    /** 数量 */
    @Excel(name = "数量")
    private Long num;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 每人限领张数 */
    @Excel(name = "每人限领张数")
    private Long perLimit;

    /** 使用门槛 */
    @Excel(name = "使用门槛")
    private BigDecimal minPoint;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 使用类型[0->全场通用；1->指定分类；2->指定商品] */
    @Excel(name = "使用类型[0->全场通用；1->指定分类；2->指定商品]")
    private Integer useType;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    /** 发行数量 */
    @Excel(name = "发行数量")
    private Long publishCount;

    /** 已使用数量 */
    @Excel(name = "已使用数量")
    private Long useCount;

    /** 领取数量 */
    @Excel(name = "领取数量")
    private Long receiveCount;

    /** 可以领取的开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "可以领取的开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enableStartTime;

    /** 可以领取的结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "可以领取的结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enableEndTime;

    /** 优惠码 */
    @Excel(name = "优惠码")
    private String code;

    /** 可以领取的会员等级[0->不限等级，其他-对应等级] */
    @Excel(name = "可以领取的会员等级[0->不限等级，其他-对应等级]")
    private Integer memberLevel;

    /** 发布状态[0-未发布，1-已发布] */
    @Excel(name = "发布状态[0-未发布，1-已发布]")
    private Integer publish;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCouponType(Integer couponType) 
    {
        this.couponType = couponType;
    }

    public Integer getCouponType() 
    {
        return couponType;
    }
    public void setCouponImg(String couponImg) 
    {
        this.couponImg = couponImg;
    }

    public String getCouponImg() 
    {
        return couponImg;
    }
    public void setCouponName(String couponName) 
    {
        this.couponName = couponName;
    }

    public String getCouponName() 
    {
        return couponName;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setPerLimit(Long perLimit) 
    {
        this.perLimit = perLimit;
    }

    public Long getPerLimit() 
    {
        return perLimit;
    }
    public void setMinPoint(BigDecimal minPoint) 
    {
        this.minPoint = minPoint;
    }

    public BigDecimal getMinPoint() 
    {
        return minPoint;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setUseType(Integer useType) 
    {
        this.useType = useType;
    }

    public Integer getUseType() 
    {
        return useType;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }
    public void setPublishCount(Long publishCount) 
    {
        this.publishCount = publishCount;
    }

    public Long getPublishCount() 
    {
        return publishCount;
    }
    public void setUseCount(Long useCount) 
    {
        this.useCount = useCount;
    }

    public Long getUseCount() 
    {
        return useCount;
    }
    public void setReceiveCount(Long receiveCount) 
    {
        this.receiveCount = receiveCount;
    }

    public Long getReceiveCount() 
    {
        return receiveCount;
    }
    public void setEnableStartTime(Date enableStartTime) 
    {
        this.enableStartTime = enableStartTime;
    }

    public Date getEnableStartTime() 
    {
        return enableStartTime;
    }
    public void setEnableEndTime(Date enableEndTime) 
    {
        this.enableEndTime = enableEndTime;
    }

    public Date getEnableEndTime() 
    {
        return enableEndTime;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setMemberLevel(Integer memberLevel) 
    {
        this.memberLevel = memberLevel;
    }

    public Integer getMemberLevel() 
    {
        return memberLevel;
    }
    public void setPublish(Integer publish) 
    {
        this.publish = publish;
    }

    public Integer getPublish() 
    {
        return publish;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("couponType", getCouponType())
            .append("couponImg", getCouponImg())
            .append("couponName", getCouponName())
            .append("num", getNum())
            .append("amount", getAmount())
            .append("perLimit", getPerLimit())
            .append("minPoint", getMinPoint())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("useType", getUseType())
            .append("note", getNote())
            .append("publishCount", getPublishCount())
            .append("useCount", getUseCount())
            .append("receiveCount", getReceiveCount())
            .append("enableStartTime", getEnableStartTime())
            .append("enableEndTime", getEnableEndTime())
            .append("code", getCode())
            .append("memberLevel", getMemberLevel())
            .append("publish", getPublish())
            .toString();
    }
}
