package com.ruoyi.ware.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 仓库信息对象 wms_ware_info
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public class WareInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 仓库名 */
    @Excel(name = "仓库名")
    private String name;

    /** 仓库地址 */
    @Excel(name = "仓库地址")
    private String address;

    /** 区域编码 */
    @Excel(name = "区域编码")
    private String areacode;

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
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setAreacode(String areacode) 
    {
        this.areacode = areacode;
    }

    public String getAreacode() 
    {
        return areacode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("address", getAddress())
            .append("areacode", getAreacode())
            .toString();
    }
}
