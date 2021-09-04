package com.ruoyi.order.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderSubmitVo {
    private Long addrId;
    private Integer payType;
    //商品从服务中重新获取
    //优惠 发票
    private String token;
    private BigDecimal payPrice;
    //订单备注
    private String note;
    private String orderToken;
}
