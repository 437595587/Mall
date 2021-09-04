package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("mall-cart")
public interface RemoteCartService {

    @GetMapping("/currentUserCartItems")
    public R<List<OrderItemVo>> getCurrentUserCartItems();
}
