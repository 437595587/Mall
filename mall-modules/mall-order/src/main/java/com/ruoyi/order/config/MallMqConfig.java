package com.ruoyi.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMq只要有声明的交换机或者队列 再次运行属性不会覆盖
 */
@Configuration
public class MallMqConfig {

    // @RabbitListener(queues = "order.release.order.queue")
    // public void listener(Order order, Channel channel, Message message) throws IOException {
    //     System.out.println("收到过期订单信息：" + order.getOrderSn() + "准备关闭订单");
    //     MessageProperties messageProperties = message.getMessageProperties();
    //     channel.basicAck(messageProperties.getDeliveryTag(), false);
    // }

    @Bean
    public Queue orderDelayQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "order.event.exchange");
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        arguments.put("x-message-ttl", 30 * 60 * 1000);
        return new Queue("order.delay.queue", true, false, false, arguments);
    }

    @Bean
    public Queue orderReleaseQueue() {
        return new Queue("order.release.order.queue", true, false, false);
    }

    @Bean
    public Exchange orderEventExchange() {
        return new TopicExchange("order.event.exchange", true, false);
    }

    @Bean
    public Binding orderCreateBinding() {
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order.event.exchange",
                "order.create.order",
                null);
    }

    @Bean
    public Binding orderReleaseBinding() {
        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order.event.exchange",
                "order.release.order",
                null);
    }

    /**
     * 订单释放直接和库存释放进行绑定
     * @return
     */
    @Bean
    public Binding orderReleaseOtherBinding() {
        return new Binding("stock.release.stock.queue",
                Binding.DestinationType.QUEUE,
                "order.event.exchange",
                "order.release.other.#",
                null);
    }
}
