package com.ruoyi.order.domain.vo;

import com.ruoyi.order.domain.Order;
import lombok.Data;

@Data
public class SubmitOrderResponseVo {
    private Order order;
    private Integer code; //200成功
}
