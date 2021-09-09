package com.ruoyi.order.service;

import com.ruoyi.mall.common.core.to.mq.SeckillOrderTo;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.domain.vo.*;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 订单Service接口
 *
 * @author xuxing
 * @date 2021-08-31
 */
public interface IOrderService
{
    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    public Order selectOrderById(Long id);

    /**
     * 查询订单列表
     *
     * @param order 订单
     * @return 订单集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增订单
     *
     * @param order 订单
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteOrderByIds(Long[] ids);

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    public int deleteOrderById(Long id);

    /**
     * 订单确认页返回需要用的数据
     */
    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);

    Order selectOrderByOrderSn(String orderSn);

    void closeOrder(Order order);

    PayVo getOrderPay(String orderSn);

    List<Order> selectOrderWithItem();

    String handlePayResult(PayAsyncVo vo) throws ParseException;

    void updateOrderByOrderSn(Order order);

    void createSeckillOrder(SeckillOrderTo seckillOrder);
}
