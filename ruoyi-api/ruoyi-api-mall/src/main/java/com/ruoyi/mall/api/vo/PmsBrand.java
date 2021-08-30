package com.ruoyi.mall.api.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.valid.AddGroup;
import com.ruoyi.common.core.valid.EditGroup;
import com.ruoyi.common.core.valid.ListValue;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌对象 pms_brand
 *
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 品牌id */
    @NotNull(message = "修改必须指定id", groups = EditGroup.class)
    @Null(message = "新增不能指定id", groups = AddGroup.class)
    private Long brandId;

    /** 品牌名 */
    @NotBlank(message = "品牌名必须提交", groups = {AddGroup.class})
    @Excel(name = "品牌名")
    private String name;

    /** 品牌logo地址 */
    @NotBlank(groups = AddGroup.class)
    @URL(message = "logo必须是一个合法的url地址", groups = {AddGroup.class, EditGroup.class})
    @Excel(name = "品牌logo地址")
    private String logo;

    /** 介绍 */
    @Excel(name = "介绍")
    private String descript;

    /** 显示状态[0-不显示；1-显示] */
    @NotNull(groups = AddGroup.class)
    @ListValue(value = {0, 1}, groups = {AddGroup.class, EditGroup.class})
    @Excel(name = "显示状态[0-不显示；1-显示]")
    private Integer showStatus;

    /** 检索首字母 */
    @NotEmpty(groups = AddGroup.class)
    @Pattern(regexp = "^[a-zA-z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, EditGroup.class})
    @Excel(name = "检索首字母")
    private String firstLetter;

    /** 排序 */
    @NotNull(groups = AddGroup.class)
    @Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class, EditGroup.class})
    @Excel(name = "排序")
    private Long sort;

    public void setBrandId(Long brandId)
    {
        this.brandId = brandId;
    }

    public Long getBrandId()
    {
        return brandId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getLogo()
    {
        return logo;
    }
    public void setDescript(String descript)
    {
        this.descript = descript;
    }

    public String getDescript()
    {
        return descript;
    }
    public void setShowStatus(Integer showStatus)
    {
        this.showStatus = showStatus;
    }

    public Integer getShowStatus()
    {
        return showStatus;
    }
    public void setFirstLetter(String firstLetter)
    {
        this.firstLetter = firstLetter;
    }

    public String getFirstLetter()
    {
        return firstLetter;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("brandId", getBrandId())
            .append("name", getName())
            .append("logo", getLogo())
            .append("descript", getDescript())
            .append("showStatus", getShowStatus())
            .append("firstLetter", getFirstLetter())
            .append("sort", getSort())
            .toString();
    }
}
