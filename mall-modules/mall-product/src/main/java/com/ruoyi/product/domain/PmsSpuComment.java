package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 商品评价对象 pms_spu_comment
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsSpuComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** sku_id */
    @Excel(name = "sku_id")
    private Long skuId;

    /** spu_id */
    @Excel(name = "spu_id")
    private Long spuId;

    /** 商品名字 */
    @Excel(name = "商品名字")
    private String spuName;

    /** 会员昵称 */
    @Excel(name = "会员昵称")
    private String memberNickName;

    /** 星级 */
    @Excel(name = "星级")
    private Integer star;

    /** 会员ip */
    @Excel(name = "会员ip")
    private String memberIp;

    /** 显示状态[0-不显示，1-显示] */
    @Excel(name = "显示状态[0-不显示，1-显示]")
    private Integer showStatus;

    /** 购买时属性组合 */
    @Excel(name = "购买时属性组合")
    private String spuAttributes;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likesCount;

    /** 回复数 */
    @Excel(name = "回复数")
    private Long replyCount;

    /** 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]] */
    @Excel(name = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
    private String resources;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String memberIcon;

    /** 评论类型[0 - 对商品的直接评论，1 - 对评论的回复] */
    @Excel(name = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
    private Integer commentType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
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
    public void setMemberNickName(String memberNickName) 
    {
        this.memberNickName = memberNickName;
    }

    public String getMemberNickName() 
    {
        return memberNickName;
    }
    public void setStar(Integer star) 
    {
        this.star = star;
    }

    public Integer getStar() 
    {
        return star;
    }
    public void setMemberIp(String memberIp) 
    {
        this.memberIp = memberIp;
    }

    public String getMemberIp() 
    {
        return memberIp;
    }
    public void setShowStatus(Integer showStatus) 
    {
        this.showStatus = showStatus;
    }

    public Integer getShowStatus() 
    {
        return showStatus;
    }
    public void setSpuAttributes(String spuAttributes) 
    {
        this.spuAttributes = spuAttributes;
    }

    public String getSpuAttributes() 
    {
        return spuAttributes;
    }
    public void setLikesCount(Long likesCount) 
    {
        this.likesCount = likesCount;
    }

    public Long getLikesCount() 
    {
        return likesCount;
    }
    public void setReplyCount(Long replyCount) 
    {
        this.replyCount = replyCount;
    }

    public Long getReplyCount() 
    {
        return replyCount;
    }
    public void setResources(String resources) 
    {
        this.resources = resources;
    }

    public String getResources() 
    {
        return resources;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setMemberIcon(String memberIcon) 
    {
        this.memberIcon = memberIcon;
    }

    public String getMemberIcon() 
    {
        return memberIcon;
    }
    public void setCommentType(Integer commentType) 
    {
        this.commentType = commentType;
    }

    public Integer getCommentType() 
    {
        return commentType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("skuId", getSkuId())
            .append("spuId", getSpuId())
            .append("spuName", getSpuName())
            .append("memberNickName", getMemberNickName())
            .append("star", getStar())
            .append("memberIp", getMemberIp())
            .append("createTime", getCreateTime())
            .append("showStatus", getShowStatus())
            .append("spuAttributes", getSpuAttributes())
            .append("likesCount", getLikesCount())
            .append("replyCount", getReplyCount())
            .append("resources", getResources())
            .append("content", getContent())
            .append("memberIcon", getMemberIcon())
            .append("commentType", getCommentType())
            .toString();
    }
}
