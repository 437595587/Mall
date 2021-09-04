package com.ruoyi.member.web;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.RemoteOrderService;
import com.ruoyi.mall.api.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MemberWebController {

    @Autowired
    private RemoteOrderService remoteOrderService;

    @GetMapping("/memberOrder.html")
    public String memberOrderPage(Integer pageNum, Model model) {
        //当前登录用户的所有订单列表数据
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        R<List<Order>> r = remoteOrderService.listOrderWithItem(pageNum, 10);
        model.addAttribute("orders", r.getData());
        return "orderList";
    }
}
