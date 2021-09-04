package com.ruoyi.order.domain.vo;

import com.ruoyi.mall.api.vo.MemberReceiveAddress;
import com.ruoyi.mall.api.vo.OrderItemVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class OrderConfirmVo {
    //收获地址
    private List<MemberReceiveAddress> address;
    //所有选中的购物项
    private List<OrderItemVo> items;
    //发票记录...
    //优惠卷信息...
    private Long integration;
    //订单总额
    private BigDecimal total;
    //应付
    private BigDecimal payPrice;

    //TODO 重量
    private BigDecimal weight;

    //防重令牌
    private String orderToken;

    private Map<Long, Boolean> stocks;

    //商品数量
    private Integer count;

    public Integer getCount() {
        if (items != null && items.size() > 0) {
            return items.size();
        } else {
            return 0;
        }
    }

    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal(0);
        if (items != null && items.size() > 0) {
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount()));
                sum = sum.add(multiply);
            }
        }
        return sum;
    }

    public BigDecimal getPayPrice() {
        return getTotal();
    }
}
