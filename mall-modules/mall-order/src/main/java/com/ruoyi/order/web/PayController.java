package com.ruoyi.order.web;

import com.alipay.api.AlipayApiException;
import com.ruoyi.order.config.pay.AlipayTemplate;
import com.ruoyi.order.domain.vo.PayVo;
import com.ruoyi.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayController {

    @Autowired
    private AlipayTemplate alipayTemplate;

    @Autowired
    private IOrderService orderService;

    /**
     * 1、将支付页让浏览器展示
     * 2、支付成功以后，我们要跳到用户的订单列表页
     * @param orderSn 订单号
     * @return 支付页面
     * @throws AlipayApiException 异常
     */
    @ResponseBody
    @GetMapping(value = "/payOrder", produces = "text/html")
    public String payOrder(@RequestParam("orderSn") String orderSn) throws AlipayApiException {
        //获取到支付宝给我们传来的所有请求数据

        PayVo payVo = orderService.getOrderPay(orderSn);
        return alipayTemplate.pay(payVo);
    }
}
