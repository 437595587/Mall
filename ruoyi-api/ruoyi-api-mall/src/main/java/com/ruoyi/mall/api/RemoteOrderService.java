package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.vo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("mall-order")
public interface RemoteOrderService {

    @GetMapping("/order/{orderSn}/status")
    public R<Order> getOrderStatus(@PathVariable("orderSn") String orderSn);

    @GetMapping("/order/listWithItem")
    public R<List<Order>> listOrderWithItem(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);
}
