package com.ruoyi.coupon.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 秒杀活动场次对象 sms_seckill_session
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public class SeckillSession extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 场次名称 */
    @Excel(name = "场次名称")
    private String name;

    /** 每日开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "每日开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 每日结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "每日结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private Integer status;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
