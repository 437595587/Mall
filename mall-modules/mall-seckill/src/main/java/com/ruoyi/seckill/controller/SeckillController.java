package com.ruoyi.seckill.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.seckill.domain.to.SeckillSkuRedisTo;
import com.ruoyi.seckill.service.ISeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SeckillController {

    @Autowired
    private ISeckillService seckillService;

    /**
     * 返回当前时间可以参与的秒杀商品信息
     * @return 结果
     */
    @ResponseBody
    @GetMapping("/currentSeckillSkus")
    public R<List<SeckillSkuRedisTo>> getCurrentSeckillSkus() {
        List<SeckillSkuRedisTo> vos = seckillService.selectCurrentSeckillSkus();
        return R.ok(vos);
    }

    @ResponseBody
    @GetMapping("/skuSeckill/{skuId}")
    public R<SeckillSkuRedisTo> getSkuSeckillInfo(@PathVariable("skuId") Long skuId) {
        SeckillSkuRedisTo to = seckillService.selectSkuSeckillInfo(skuId);
        return R.ok(to);
    }

    @GetMapping("/kill")
    public String seckill(@RequestParam("killId") String killId,
                          @RequestParam("key") String key,
                          @RequestParam("num") Integer num,
                          Model model) {
        //1、判断是否登录
        String orderSn = seckillService.kill(killId, key, num);
        model.addAttribute("orderSn", orderSn);
        return "success";
    }
}
