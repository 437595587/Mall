package com.ruoyi.mall.api.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FareVo {
    private MemberReceiveAddress address;
    private BigDecimal fare;
}
