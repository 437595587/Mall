package com.ruoyi.ware.listener;

import com.rabbitmq.client.Channel;
import com.ruoyi.mall.api.vo.Order;
import com.ruoyi.mall.common.core.to.mq.StockLockedTo;
import com.ruoyi.ware.service.IWareSkuService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "stock.release.stock.queue")
public class StockReleaseListener {

    @Autowired
    IWareSkuService wareSkuService;


    @RabbitHandler
    public void handleStockLockedRelease(StockLockedTo to, Message message, Channel channel) throws IOException {
        // System.out.println("收到解锁库存消息：");
        try {
            wareSkuService.unlockStock(to);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    @RabbitHandler
    public void handleOrderCloseRelease(Order order, Message message, Channel channel) throws IOException {
        // System.out.println("订单关闭准备解锁库存：");
        try {
            wareSkuService.unlockStock(order);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
