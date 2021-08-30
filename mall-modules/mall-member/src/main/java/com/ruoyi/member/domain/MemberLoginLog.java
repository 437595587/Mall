package com.ruoyi.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 会员登录记录对象 ums_member_login_log
 * 
 * @author xuxing
 * @date 2021-08-22
 */
public class MemberLoginLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** member_id */
    @Excel(name = "member_id")
    private Long memberId;

    /** ip */
    @Excel(name = "ip")
    private String ip;

    /** city */
    @Excel(name = "city")
    private String city;

    /** 登录类型[1-web，2-app] */
    @Excel(name = "登录类型[1-web，2-app]")
    private Integer loginType;

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
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setLoginType(Integer loginType) 
    {
        this.loginType = loginType;
    }

    public Integer getLoginType() 
    {
        return loginType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("createTime", getCreateTime())
            .append("ip", getIp())
            .append("city", getCity())
            .append("loginType", getLoginType())
            .toString();
    }
}
