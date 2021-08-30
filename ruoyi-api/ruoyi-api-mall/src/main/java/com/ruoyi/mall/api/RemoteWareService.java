package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.to.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-ware")
public interface RemoteWareService {

    //查询sku是否有库存
    @PostMapping("/wareSku/hasStock")
    public R<List<SkuHasStockVo>> getSkusHasStock(@RequestBody List<Long> skuIds);
}
