package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.to.SkuReductionTo;
import com.ruoyi.mall.api.to.SpuBoundTo;
import com.ruoyi.mall.api.vo.SeckillSession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-coupon")
public interface RemoteCouponService {

    /**
     * 1、 @requestBody将这个对象转为json
     * 2、 找到mall-coupon服务 给/spuBounds发送请求，将上一步的json放在请求体位置，发送请求
     * 3、 对方服务收到请求，请求体里有json数据
     * (@RequestBody SpuBounds spuBounds);将请求体的json转为spuBounds
     * 只要json模型是兼容的，双方服务无需使用同一个vo
     *
     * @param spuBoundTo
     * @return
     */
    @PostMapping("/spuBounds")
    public R<Boolean> addSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/skuFullReduction/addSkuSmsInfo")
    public R<Boolean> addSkuSmsInfo(@RequestBody SkuReductionTo skuReductionTo);

    @GetMapping("/seckillSession/latest3DaysSession")
    public R<List<SeckillSession>> getLatest3DaysSession();
}
