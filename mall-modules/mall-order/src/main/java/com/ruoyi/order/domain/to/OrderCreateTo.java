package com.ruoyi.order.domain.to;

import com.ruoyi.order.domain.Order;
import com.ruoyi.order.domain.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateTo {
    private Order order;
    private List<OrderItem> orderItems;
    private BigDecimal payPrice;
    private BigDecimal fare;
}
