package com.ruoyi.ware.domain.vo;

import com.ruoyi.mall.api.vo.MemberReceiveAddress;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FareVo {
    private MemberReceiveAddress address;
    private BigDecimal fare;
}
