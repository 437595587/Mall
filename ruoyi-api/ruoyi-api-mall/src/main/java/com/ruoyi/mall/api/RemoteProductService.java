package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.vo.PmsAttrRespVo;
import com.ruoyi.mall.api.vo.PmsBrand;
import com.ruoyi.mall.api.vo.PmsSkuInfo;
import com.ruoyi.mall.api.vo.PmsSpuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient("mall-product")
public interface RemoteProductService {

    @GetMapping(value = "/attr/{attrId}")
    public R<PmsAttrRespVo> getAttrInfo(@PathVariable("attrId") Long attrId);

    @GetMapping("/brand/infos")
    public R<List<PmsBrand>> getBrandInfos(@RequestParam("brandIds") List<Long> brandIds);

    @GetMapping(value = "/skuInfo/{skuId}")
    public R<PmsSkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId);

    @GetMapping("/saleAttrValue/skuSaleAttrValues/{skuId}")
    public R<List<String>> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId);

    @GetMapping("/skuInfo/{skuId}/price")
    public R<BigDecimal> getSkuPrice(@PathVariable("skuId") Long skuId);

    @GetMapping("/spuInfo/{id}/skuId")
    public R<PmsSpuInfo> getSpuInfoBySkuId(@PathVariable("id") Long skuId);
}
