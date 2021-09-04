package com.ruoyi.order;

import com.ruoyi.order.domain.Order;
import com.ruoyi.order.domain.OrderReturnReason;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallOrderApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void createExchange() {
        DirectExchange directExchange = new DirectExchange("hello.java.exchange", true, false);
        amqpAdmin.declareExchange(directExchange);
        System.out.println(directExchange);
    }

    @Test
    public void createQueue() {
        Queue queue = new Queue("hello.java.queue", true, false, false);
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void creatBinding() {
        Binding binding = new Binding("hello.java.queue", Binding.DestinationType.QUEUE, "hello.java.exchange", "hello.#", null);
        amqpAdmin.declareBinding(binding);
    }

    @Test
    public void sendMessage() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                OrderReturnReason orderReturnReason = new OrderReturnReason();
                orderReturnReason.setId(1L);
                orderReturnReason.setCreateTime(new Date());
                orderReturnReason.setName("hello world");
                rabbitTemplate.convertAndSend("hello.java.exchange", "hello.java", orderReturnReason);
            } else {
                Order order = new Order();
                order.setOrderSn(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("hello.java.exchange", "hello.java", order);
            }
        }
    }
}
