package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * spu信息介绍对象 pms_spu_info_desc
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public class PmsSpuInfoDesc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long spuId;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String decript;

    public void setSpuId(Long spuId) 
    {
        this.spuId = spuId;
    }

    public Long getSpuId() 
    {
        return spuId;
    }
    public void setDecript(String decript) 
    {
        this.decript = decript;
    }

    public String getDecript() 
    {
        return decript;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("spuId", getSpuId())
            .append("decript", getDecript())
            .toString();
    }
}
