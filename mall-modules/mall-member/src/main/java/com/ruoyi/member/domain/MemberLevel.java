package com.ruoyi.member.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 会员等级对象 ums_member_level
 * 
 * @author xuxing
 * @date 2021-08-22
 */
public class MemberLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 等级名称 */
    @Excel(name = "等级名称")
    private String name;

    /** 等级需要的成长值 */
    @Excel(name = "等级需要的成长值")
    private Long growthPoint;

    /** 是否为默认等级[0-&gt;不是；1-&gt;是] */
    @Excel(name = "是否为默认等级[0-&gt;不是；1-&gt;是]")
    private Integer defaultStatus;

    /** 免运费标准 */
    @Excel(name = "免运费标准")
    private BigDecimal freeFreightPoint;

    /** 每次评价获取的成长值 */
    @Excel(name = "每次评价获取的成长值")
    private Long commentGrowthPoint;

    /** 是否有免邮特权 */
    @Excel(name = "是否有免邮特权")
    private Integer priviledgeFreeFreight;

    /** 是否有会员价格特权 */
    @Excel(name = "是否有会员价格特权")
    private Integer priviledgeMemberPrice;

    /** 是否有生日特权 */
    @Excel(name = "是否有生日特权")
    private Integer priviledgeBirthday;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setGrowthPoint(Long growthPoint) 
    {
        this.growthPoint = growthPoint;
    }

    public Long getGrowthPoint() 
    {
        return growthPoint;
    }
    public void setDefaultStatus(Integer defaultStatus) 
    {
        this.defaultStatus = defaultStatus;
    }

    public Integer getDefaultStatus() 
    {
        return defaultStatus;
    }
    public void setFreeFreightPoint(BigDecimal freeFreightPoint) 
    {
        this.freeFreightPoint = freeFreightPoint;
    }

    public BigDecimal getFreeFreightPoint() 
    {
        return freeFreightPoint;
    }
    public void setCommentGrowthPoint(Long commentGrowthPoint) 
    {
        this.commentGrowthPoint = commentGrowthPoint;
    }

    public Long getCommentGrowthPoint() 
    {
        return commentGrowthPoint;
    }
    public void setPriviledgeFreeFreight(Integer priviledgeFreeFreight) 
    {
        this.priviledgeFreeFreight = priviledgeFreeFreight;
    }

    public Integer getPriviledgeFreeFreight() 
    {
        return priviledgeFreeFreight;
    }
    public void setPriviledgeMemberPrice(Integer priviledgeMemberPrice) 
    {
        this.priviledgeMemberPrice = priviledgeMemberPrice;
    }

    public Integer getPriviledgeMemberPrice() 
    {
        return priviledgeMemberPrice;
    }
    public void setPriviledgeBirthday(Integer priviledgeBirthday) 
    {
        this.priviledgeBirthday = priviledgeBirthday;
    }

    public Integer getPriviledgeBirthday() 
    {
        return priviledgeBirthday;
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
            .append("name", getName())
            .append("growthPoint", getGrowthPoint())
            .append("defaultStatus", getDefaultStatus())
            .append("freeFreightPoint", getFreeFreightPoint())
            .append("commentGrowthPoint", getCommentGrowthPoint())
            .append("priviledgeFreeFreight", getPriviledgeFreeFreight())
            .append("priviledgeMemberPrice", getPriviledgeMemberPrice())
            .append("priviledgeBirthday", getPriviledgeBirthday())
            .append("note", getNote())
            .toString();
    }
}
