package com.ruoyi.coupon.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 首页轮播广告对象 sms_home_adv
 *
 * @author xuxing
 * @date 2021-08-23
 */
public class HomeAdv extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名字 */
    @Excel(name = "名字")
    private String name;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String pic;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long clickCount;

    /** 广告详情连接地址 */
    @Excel(name = "广告详情连接地址")
    private String url;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 发布者 */
    @Excel(name = "发布者")
    private Long publisherId;

    /** 审核者 */
    @Excel(name = "审核者")
    private Long authId;

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
    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public String getPic()
    {
        return pic;
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
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setClickCount(Long clickCount)
    {
        this.clickCount = clickCount;
    }

    public Long getClickCount()
    {
        return clickCount;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setNote(String note)
    {
        this.note = note;
    }

    public String getNote()
    {
        return note;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setPublisherId(Long publisherId)
    {
        this.publisherId = publisherId;
    }

    public Long getPublisherId()
    {
        return publisherId;
    }
    public void setAuthId(Long authId)
    {
        this.authId = authId;
    }

    public Long getAuthId()
    {
        return authId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("pic", getPic())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("clickCount", getClickCount())
            .append("url", getUrl())
            .append("note", getNote())
            .append("sort", getSort())
            .append("publisherId", getPublisherId())
            .append("authId", getAuthId())
            .toString();
    }
}
