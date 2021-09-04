package com.ruoyi.order.web;

import com.ruoyi.order.domain.vo.OrderConfirmVo;
import com.ruoyi.order.domain.vo.OrderSubmitVo;
import com.ruoyi.order.domain.vo.SubmitOrderResponseVo;
import com.ruoyi.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

@Controller
public class OrderWebController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/toTrade")
    public String toTrade(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("orderConfirmData", confirmVo);
        return "confirm";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo vo, Model model, RedirectAttributes redirectAttributes) {
        SubmitOrderResponseVo responseVo = null;
        try {
            responseVo = orderService.submitOrder(vo);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "下单失败");
            return "redirect:http://order.mall.com/toTrade";
        }
        if (responseVo.getCode() == 200) {
            //成功
            model.addAttribute("submitOrderResp", responseVo);
            return "pay";
        } else {
            String msg = "下单失败：";
            switch (responseVo.getCode()) {
                case 500:
                    msg += "订单信息过期，请刷新再次提交";
                    break;
                case 501:
                    msg += "订单商品价格发生变化，请确认后再次提交";
                    break;
                case 502:
                    msg += "库存锁定失败，库存商品不足";
                    break;
            }
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:http://order.mall.com/toTrade";
        }
    }
}
