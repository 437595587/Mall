package com.ruoyi.coupon.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】对象 sms_home_subject
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public class HomeSubject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 专题名字 */
    @Excel(name = "专题名字")
    private String name;

    /** 专题标题 */
    @Excel(name = "专题标题")
    private String title;

    /** 专题副标题 */
    @Excel(name = "专题副标题")
    private String subTitle;

    /** 显示状态 */
    @Excel(name = "显示状态")
    private Integer status;

    /** 详情连接 */
    @Excel(name = "详情连接")
    private String url;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 专题图片地址 */
    @Excel(name = "专题图片地址")
    private String img;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setSubTitle(String subTitle) 
    {
        this.subTitle = subTitle;
    }

    public String getSubTitle() 
    {
        return subTitle;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("title", getTitle())
            .append("subTitle", getSubTitle())
            .append("status", getStatus())
            .append("url", getUrl())
            .append("sort", getSort())
            .append("img", getImg())
            .toString();
    }
}
