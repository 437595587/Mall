package com.ruoyi.order.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 订单配置信息对象 oms_order_setting
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public class OrderSetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 秒杀订单超时关闭时间(分) */
    @Excel(name = "秒杀订单超时关闭时间(分)")
    private Long flashOrderOvertime;

    /** 正常订单超时时间(分) */
    @Excel(name = "正常订单超时时间(分)")
    private Long normalOrderOvertime;

    /** 发货后自动确认收货时间（天） */
    @Excel(name = "发货后自动确认收货时间", readConverterExp = "天=")
    private Long confirmOvertime;

    /** 自动完成交易时间，不能申请退货（天） */
    @Excel(name = "自动完成交易时间，不能申请退货", readConverterExp = "天=")
    private Long finishOvertime;

    /** 订单完成后自动好评时间（天） */
    @Excel(name = "订单完成后自动好评时间", readConverterExp = "天=")
    private Long commentOvertime;

    /** 会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】 */
    @Excel(name = "会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】")
    private Integer memberLevel;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFlashOrderOvertime(Long flashOrderOvertime) 
    {
        this.flashOrderOvertime = flashOrderOvertime;
    }

    public Long getFlashOrderOvertime() 
    {
        return flashOrderOvertime;
    }
    public void setNormalOrderOvertime(Long normalOrderOvertime) 
    {
        this.normalOrderOvertime = normalOrderOvertime;
    }

    public Long getNormalOrderOvertime() 
    {
        return normalOrderOvertime;
    }
    public void setConfirmOvertime(Long confirmOvertime) 
    {
        this.confirmOvertime = confirmOvertime;
    }

    public Long getConfirmOvertime() 
    {
        return confirmOvertime;
    }
    public void setFinishOvertime(Long finishOvertime) 
    {
        this.finishOvertime = finishOvertime;
    }

    public Long getFinishOvertime() 
    {
        return finishOvertime;
    }
    public void setCommentOvertime(Long commentOvertime) 
    {
        this.commentOvertime = commentOvertime;
    }

    public Long getCommentOvertime() 
    {
        return commentOvertime;
    }
    public void setMemberLevel(Integer memberLevel) 
    {
        this.memberLevel = memberLevel;
    }

    public Integer getMemberLevel() 
    {
        return memberLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("flashOrderOvertime", getFlashOrderOvertime())
            .append("normalOrderOvertime", getNormalOrderOvertime())
            .append("confirmOvertime", getConfirmOvertime())
            .append("finishOvertime", getFinishOvertime())
            .append("commentOvertime", getCommentOvertime())
            .append("memberLevel", getMemberLevel())
            .toString();
    }
}
