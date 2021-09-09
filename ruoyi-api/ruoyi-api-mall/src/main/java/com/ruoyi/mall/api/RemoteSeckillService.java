package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.factory.RemoteSeckillFallbackFactory;
import com.ruoyi.mall.api.vo.SeckillInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "remoteSeckillService", value = "mall-seckill", fallbackFactory = RemoteSeckillFallbackFactory.class)
public interface RemoteSeckillService {

    @GetMapping("/skuSeckill/{skuId}")
    public R<SeckillInfoVo> getSkuSeckillInfo(@PathVariable("skuId") Long skuId);
}
