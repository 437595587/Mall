package com.ruoyi.mall.api.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 会员收货地址对象 ums_member_receive_address
 *
 * @author xuxing
 * @date 2021-08-22
 */
public class MemberReceiveAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** member_id */
    @Excel(name = "member_id")
    private Long memberId;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String postCode;

    /** 省份/直辖市 */
    @Excel(name = "省份/直辖市")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String region;

    /** 详细地址(街道) */
    @Excel(name = "详细地址(街道)")
    private String detailAddress;

    /** 省市区代码 */
    @Excel(name = "省市区代码")
    private String areacode;

    /** 是否默认 */
    @Excel(name = "是否默认")
    private Integer defaultStatus;

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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getPostCode()
    {
        return postCode;
    }
    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getProvince()
    {
        return province;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getRegion()
    {
        return region;
    }
    public void setDetailAddress(String detailAddress)
    {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress()
    {
        return detailAddress;
    }
    public void setAreacode(String areacode)
    {
        this.areacode = areacode;
    }

    public String getAreacode()
    {
        return areacode;
    }
    public void setDefaultStatus(Integer defaultStatus)
    {
        this.defaultStatus = defaultStatus;
    }

    public Integer getDefaultStatus()
    {
        return defaultStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("postCode", getPostCode())
            .append("province", getProvince())
            .append("city", getCity())
            .append("region", getRegion())
            .append("detailAddress", getDetailAddress())
            .append("areacode", getAreacode())
            .append("defaultStatus", getDefaultStatus())
            .toString();
    }
}
