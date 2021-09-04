package com.ruoyi.order.web;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.IdUtils;
import com.ruoyi.order.domain.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ResponseBody
    @GetMapping("/test/createOrder")
    public String createOrderTest() {
        Order order = new Order();
        order.setOrderSn(IdUtils.fastSimpleUUID());
        order.setCreateTime(DateUtils.getNowDate());
        rabbitTemplate.convertAndSend("order.event.exchange", "order.create.order", order);
        return "ok";
    }

    @GetMapping("/{page}.html")
    public String toPage(@PathVariable("page") String page) {
        return page;
    }


}
