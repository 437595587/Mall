package com.ruoyi.member.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 会员统计信息对象 ums_member_statistics_info
 * 
 * @author xuxing
 * @date 2021-08-22
 */
public class MemberStatisticsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 会员id */
    @Excel(name = "会员id")
    private Long memberId;

    /** 累计消费金额 */
    @Excel(name = "累计消费金额")
    private BigDecimal consumeAmount;

    /** 累计优惠金额 */
    @Excel(name = "累计优惠金额")
    private BigDecimal couponAmount;

    /** 订单数量 */
    @Excel(name = "订单数量")
    private Long orderCount;

    /** 优惠券数量 */
    @Excel(name = "优惠券数量")
    private Long couponCount;

    /** 评价数 */
    @Excel(name = "评价数")
    private Long commentCount;

    /** 退货数量 */
    @Excel(name = "退货数量")
    private Long returnOrderCount;

    /** 登录次数 */
    @Excel(name = "登录次数")
    private Long loginCount;

    /** 关注数量 */
    @Excel(name = "关注数量")
    private Long attendCount;

    /** 粉丝数量 */
    @Excel(name = "粉丝数量")
    private Long fansCount;

    /** 收藏的商品数量 */
    @Excel(name = "收藏的商品数量")
    private Long collectProductCount;

    /** 收藏的专题活动数量 */
    @Excel(name = "收藏的专题活动数量")
    private Long collectSubjectCount;

    /** 收藏的评论数量 */
    @Excel(name = "收藏的评论数量")
    private Long collectCommentCount;

    /** 邀请的朋友数量 */
    @Excel(name = "邀请的朋友数量")
    private Long inviteFriendCount;

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
    public void setConsumeAmount(BigDecimal consumeAmount) 
    {
        this.consumeAmount = consumeAmount;
    }

    public BigDecimal getConsumeAmount() 
    {
        return consumeAmount;
    }
    public void setCouponAmount(BigDecimal couponAmount) 
    {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getCouponAmount() 
    {
        return couponAmount;
    }
    public void setOrderCount(Long orderCount) 
    {
        this.orderCount = orderCount;
    }

    public Long getOrderCount() 
    {
        return orderCount;
    }
    public void setCouponCount(Long couponCount) 
    {
        this.couponCount = couponCount;
    }

    public Long getCouponCount() 
    {
        return couponCount;
    }
    public void setCommentCount(Long commentCount) 
    {
        this.commentCount = commentCount;
    }

    public Long getCommentCount() 
    {
        return commentCount;
    }
    public void setReturnOrderCount(Long returnOrderCount) 
    {
        this.returnOrderCount = returnOrderCount;
    }

    public Long getReturnOrderCount() 
    {
        return returnOrderCount;
    }
    public void setLoginCount(Long loginCount) 
    {
        this.loginCount = loginCount;
    }

    public Long getLoginCount() 
    {
        return loginCount;
    }
    public void setAttendCount(Long attendCount) 
    {
        this.attendCount = attendCount;
    }

    public Long getAttendCount() 
    {
        return attendCount;
    }
    public void setFansCount(Long fansCount) 
    {
        this.fansCount = fansCount;
    }

    public Long getFansCount() 
    {
        return fansCount;
    }
    public void setCollectProductCount(Long collectProductCount) 
    {
        this.collectProductCount = collectProductCount;
    }

    public Long getCollectProductCount() 
    {
        return collectProductCount;
    }
    public void setCollectSubjectCount(Long collectSubjectCount) 
    {
        this.collectSubjectCount = collectSubjectCount;
    }

    public Long getCollectSubjectCount() 
    {
        return collectSubjectCount;
    }
    public void setCollectCommentCount(Long collectCommentCount) 
    {
        this.collectCommentCount = collectCommentCount;
    }

    public Long getCollectCommentCount() 
    {
        return collectCommentCount;
    }
    public void setInviteFriendCount(Long inviteFriendCount) 
    {
        this.inviteFriendCount = inviteFriendCount;
    }

    public Long getInviteFriendCount() 
    {
        return inviteFriendCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("consumeAmount", getConsumeAmount())
            .append("couponAmount", getCouponAmount())
            .append("orderCount", getOrderCount())
            .append("couponCount", getCouponCount())
            .append("commentCount", getCommentCount())
            .append("returnOrderCount", getReturnOrderCount())
            .append("loginCount", getLoginCount())
            .append("attendCount", getAttendCount())
            .append("fansCount", getFansCount())
            .append("collectProductCount", getCollectProductCount())
            .append("collectSubjectCount", getCollectSubjectCount())
            .append("collectCommentCount", getCollectCommentCount())
            .append("inviteFriendCount", getInviteFriendCount())
            .toString();
    }
}
