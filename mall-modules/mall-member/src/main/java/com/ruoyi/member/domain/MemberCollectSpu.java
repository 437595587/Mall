package com.ruoyi.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 会员收藏的商品对象 ums_member_collect_spu
 * 
 * @author xuxing
 * @date 2021-08-22
 */
public class MemberCollectSpu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 会员id */
    @Excel(name = "会员id")
    private Long memberId;

    /** spu_id */
    @Excel(name = "spu_id")
    private Long spuId;

    /** spu_name */
    @Excel(name = "spu_name")
    private String spuName;

    /** spu_img */
    @Excel(name = "spu_img")
    private String spuImg;

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
    public void setSpuId(Long spuId) 
    {
        this.spuId = spuId;
    }

    public Long getSpuId() 
    {
        return spuId;
    }
    public void setSpuName(String spuName) 
    {
        this.spuName = spuName;
    }

    public String getSpuName() 
    {
        return spuName;
    }
    public void setSpuImg(String spuImg) 
    {
        this.spuImg = spuImg;
    }

    public String getSpuImg() 
    {
        return spuImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("spuId", getSpuId())
            .append("spuName", getSpuName())
            .append("spuImg", getSpuImg())
            .append("createTime", getCreateTime())
            .toString();
    }
}
