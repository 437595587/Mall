package com.ruoyi.order.service.impl;

import com.ruoyi.order.domain.OrderItem;
import com.ruoyi.order.mapper.OrderItemMapper;
import com.ruoyi.order.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单项信息Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-31
 */
@Service
public class OrderItemServiceImpl implements IOrderItemService
{
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询订单项信息
     *
     * @param id 订单项信息主键
     * @return 订单项信息
     */
    @Override
    public OrderItem selectOrderItemById(Long id)
    {
        return orderItemMapper.selectOrderItemById(id);
    }

    /**
     * 查询订单项信息列表
     *
     * @param orderItem 订单项信息
     * @return 订单项信息
     */
    @Override
    public List<OrderItem> selectOrderItemList(OrderItem orderItem)
    {
        return orderItemMapper.selectOrderItemList(orderItem);
    }

    /**
     * 新增订单项信息
     *
     * @param orderItem 订单项信息
     * @return 结果
     */
    @Override
    public int insertOrderItem(OrderItem orderItem)
    {
        return orderItemMapper.insertOrderItem(orderItem);
    }

    /**
     * 修改订单项信息
     *
     * @param orderItem 订单项信息
     * @return 结果
     */
    @Override
    public int updateOrderItem(OrderItem orderItem)
    {
        return orderItemMapper.updateOrderItem(orderItem);
    }

    /**
     * 批量删除订单项信息
     *
     * @param ids 需要删除的订单项信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemByIds(Long[] ids)
    {
        return orderItemMapper.deleteOrderItemByIds(ids);
    }

    /**
     * 删除订单项信息信息
     *
     * @param id 订单项信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemById(Long id)
    {
        return orderItemMapper.deleteOrderItemById(id);
    }
}
