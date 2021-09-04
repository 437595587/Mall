package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.to.WareSkuLockTo;
import com.ruoyi.mall.api.vo.FareVo;
import com.ruoyi.mall.api.vo.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("mall-ware")
public interface RemoteWareService {

    //查询sku是否有库存
    @PostMapping("/wareSku/hasStock")
    public R<List<SkuHasStockVo>> getSkusHasStock(@RequestBody List<Long> skuIds);

    @GetMapping("/wareInfo/fare")
    public R<FareVo> getFare(@RequestParam("addrId") Long addrId);

    @PostMapping("/wareSku/lock/order")
    public R<Boolean> orderLockStock(@RequestBody WareSkuLockTo to);
}
