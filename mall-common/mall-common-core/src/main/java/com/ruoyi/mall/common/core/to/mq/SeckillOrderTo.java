package com.ruoyi.mall.common.core.to.mq;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeckillOrderTo {
    private String orderSn; //订单号
    private Long promotionSessionId;//场次Id
    private Long skuId; //商品id
    private BigDecimal seckillPrice;//秒杀价格
    private Integer num;//购买数量
    private Long memberId; //会员id
}
