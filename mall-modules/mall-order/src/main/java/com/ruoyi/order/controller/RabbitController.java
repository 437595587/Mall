package com.ruoyi.order.controller;

import com.rabbitmq.client.Channel;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.domain.OrderReturnReason;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RabbitListener(queues = {"hello.java.queue"})
@RequestMapping("/rabbit")
@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMq")
    public String sendMessage(@RequestParam(value = "num",defaultValue = "10") Integer num) {
        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) {
                OrderReturnReason orderReturnReason = new OrderReturnReason();
                orderReturnReason.setId(1L);
                orderReturnReason.setCreateTime(new Date());
                orderReturnReason.setName("hello world");
                rabbitTemplate.convertAndSend("hello.java.exchange", "hello.java", orderReturnReason, new CorrelationData(UUID.randomUUID().toString()));
            } else {
                Order order = new Order();
                order.setOrderSn(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("hello.java.exchange", "ccc.java", order, new CorrelationData(UUID.randomUUID().toString()));
            }
        }
        return "ok";
    }

    /**
     * org.springframework.amqp.core.Message
     * <p>
     * 参数可以写以下类型：
     * 1、Message message：原生消息详细信息。头+体
     * 2、(发送的消息类型) OrderReturnReason content
     * Queue：可以多个监听，只要收到消息，队列删除消息，只有一个能收到消息。
     * 场景：
     * 1）、订单服务启动多个
     * 2）、只有一个消息完全处理完，方法运行结束，才能接受下一个消息
     * </p>
     */
    @RabbitListener(queues = {"hello.java.queue"})
    public void receiveMessage(Message message, OrderReturnReason content, Channel channel) throws InterruptedException, IOException {
        System.out.println("RabbitListener接受到消息...内容：" + content);
        Thread.sleep(1000);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    public void receiveMessage2(Message message, OrderReturnReason content, Channel channel) throws InterruptedException, IOException {
        System.out.println("RabbitHandler2-1接受到消息...内容：" + content);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        if (deliveryTag % 2 == 0) {
            channel.basicAck(deliveryTag, false);
        } else {
            //拒绝
            //requeue true：重新入队 fasle：丢弃
            channel.basicNack(deliveryTag, false, true);
            // channel.basicReject();
        }
    }

    @RabbitHandler
    public void receiveMessage2(Message message, Order content, Channel channel) throws InterruptedException, IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("RabbitHandler2-2接受到消息...内容：" + content);
        channel.basicNack(deliveryTag, false, true);
    }
}
