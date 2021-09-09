package com.ruoyi.order.listener;

import com.rabbitmq.client.Channel;
import com.ruoyi.mall.common.core.to.mq.SeckillOrderTo;
import com.ruoyi.order.service.IOrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RabbitListener(queues = "order.seckill.order.queue")
@Component
public class OrderSeckillListener {

    @Autowired
    private IOrderService orderService;

    @RabbitHandler
    public void listener(SeckillOrderTo seckillOrder, Channel channel, Message message) throws IOException {
        System.out.println("准备创建秒杀单的详细信息...");
        MessageProperties messageProperties = message.getMessageProperties();
        try {
            orderService.createSeckillOrder(seckillOrder);
            channel.basicAck(messageProperties.getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
