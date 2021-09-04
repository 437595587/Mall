package com.ruoyi.mall.api.to;

import com.ruoyi.mall.api.vo.OrderItemVo;
import lombok.Data;

import java.util.List;

@Data
public class WareSkuLockTo {
    private String orderSn;
    private List<OrderItemVo> locks;
}
