package com.ruoyi.order.listener;

import com.rabbitmq.client.Channel;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.service.IOrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "order.release.order.queue")
public class OrderCloseListener {

    @Autowired
    private IOrderService orderService;

    @RabbitHandler
    public void listener(Order order, Channel channel, Message message) throws IOException {
        // System.out.println("收到过期订单信息：" + order.getOrderSn() + "准备关闭订单");
        MessageProperties messageProperties = message.getMessageProperties();
        try {
            orderService.closeOrder(order);
            //手动调用支付宝收单或者设置自动关单
            channel.basicAck(messageProperties.getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
