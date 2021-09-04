package com.ruoyi.order.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MallRabbitConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 1、发送确认回调
     * 2、消费成功回调
     */
    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData 当前消息的唯一关联数据（消息的唯一id）
             * @param ack 消息是否成功发送
             * @param cause 失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                // 服务器收到了
                // 修改消息状态
                // System.out.println("-------------消息成功发送-------------");
                // System.out.println("correlationData = " + correlationData);
                // System.out.println("ack = " + ack);
                // System.out.println("cause = " + cause);
            }
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            // Message message = returned.getMessage();
            // int replyCode = returned.getReplyCode();
            // String replyText = returned.getReplyText();
            // String exchange = returned.getExchange();
            // String routingKey = returned.getRoutingKey();
            // System.out.println("-------------消息未成功入队回调-------------");
            // System.out.println("message = " + message);
            // System.out.println("replyCode = " + replyCode);
            // System.out.println("replyText = " + replyText);
            // System.out.println("exchange = " + exchange);
            // System.out.println("routingKey = " + routingKey);
        });
    }
}
