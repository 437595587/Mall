package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.OrderOperateHistoryMapper;
import com.ruoyi.order.domain.OrderOperateHistory;
import com.ruoyi.order.service.IOrderOperateHistoryService;

/**
 * 订单操作历史记录Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-31
 */
@Service
public class OrderOperateHistoryServiceImpl implements IOrderOperateHistoryService 
{
    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;

    /**
     * 查询订单操作历史记录
     * 
     * @param id 订单操作历史记录主键
     * @return 订单操作历史记录
     */
    @Override
    public OrderOperateHistory selectOrderOperateHistoryById(Long id)
    {
        return orderOperateHistoryMapper.selectOrderOperateHistoryById(id);
    }

    /**
     * 查询订单操作历史记录列表
     * 
     * @param orderOperateHistory 订单操作历史记录
     * @return 订单操作历史记录
     */
    @Override
    public List<OrderOperateHistory> selectOrderOperateHistoryList(OrderOperateHistory orderOperateHistory)
    {
        return orderOperateHistoryMapper.selectOrderOperateHistoryList(orderOperateHistory);
    }

    /**
     * 新增订单操作历史记录
     * 
     * @param orderOperateHistory 订单操作历史记录
     * @return 结果
     */
    @Override
    public int insertOrderOperateHistory(OrderOperateHistory orderOperateHistory)
    {
        orderOperateHistory.setCreateTime(DateUtils.getNowDate());
        return orderOperateHistoryMapper.insertOrderOperateHistory(orderOperateHistory);
    }

    /**
     * 修改订单操作历史记录
     * 
     * @param orderOperateHistory 订单操作历史记录
     * @return 结果
     */
    @Override
    public int updateOrderOperateHistory(OrderOperateHistory orderOperateHistory)
    {
        return orderOperateHistoryMapper.updateOrderOperateHistory(orderOperateHistory);
    }

    /**
     * 批量删除订单操作历史记录
     * 
     * @param ids 需要删除的订单操作历史记录主键
     * @return 结果
     */
    @Override
    public int deleteOrderOperateHistoryByIds(Long[] ids)
    {
        return orderOperateHistoryMapper.deleteOrderOperateHistoryByIds(ids);
    }

    /**
     * 删除订单操作历史记录信息
     * 
     * @param id 订单操作历史记录主键
     * @return 结果
     */
    @Override
    public int deleteOrderOperateHistoryById(Long id)
    {
        return orderOperateHistoryMapper.deleteOrderOperateHistoryById(id);
    }
}
