package com.ruoyi.ware.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MallMqConfig {

    // @RabbitListener(queues = "stock.release.stock.queue")
    // public void handle(Message message) {
    //
    // }

    @Bean
    public Exchange stockEventExchange() {
        return new TopicExchange("stock.event.exchange", true, false);
    }

    @Bean
    public Queue stockReleaseQueue() {
        return new Queue("stock.release.stock.queue", true, false, false);
    }

    @Bean
    public Queue stockDelayQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "stock.event.exchange");
        arguments.put("x-dead-letter-routing-key", "stock.release");
        arguments.put("x-message-ttl", 30 * 60 * 1000);
        return new Queue("stock.delay.queue", true, false, false, arguments);
    }

    @Bean
    public Binding stockReleaseBinding() {
        return new Binding("stock.release.stock.queue",
                Binding.DestinationType.QUEUE,
                "stock.event.exchange",
                "stock.release.#",
                null);
    }

    @Bean
    public Binding stockLockedBinding() {
        return new Binding("stock.delay.queue",
                Binding.DestinationType.QUEUE,
                "stock.event.exchange",
                "stock.locked",
                null);
    }
}
