package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.OrderOperateHistory;

/**
 * 订单操作历史记录Service接口
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public interface IOrderOperateHistoryService 
{
    /**
     * 查询订单操作历史记录
     * 
     * @param id 订单操作历史记录主键
     * @return 订单操作历史记录
     */
    public OrderOperateHistory selectOrderOperateHistoryById(Long id);

    /**
     * 查询订单操作历史记录列表
     * 
     * @param orderOperateHistory 订单操作历史记录
     * @return 订单操作历史记录集合
     */
    public List<OrderOperateHistory> selectOrderOperateHistoryList(OrderOperateHistory orderOperateHistory);

    /**
     * 新增订单操作历史记录
     * 
     * @param orderOperateHistory 订单操作历史记录
     * @return 结果
     */
    public int insertOrderOperateHistory(OrderOperateHistory orderOperateHistory);

    /**
     * 修改订单操作历史记录
     * 
     * @param orderOperateHistory 订单操作历史记录
     * @return 结果
     */
    public int updateOrderOperateHistory(OrderOperateHistory orderOperateHistory);

    /**
     * 批量删除订单操作历史记录
     * 
     * @param ids 需要删除的订单操作历史记录主键集合
     * @return 结果
     */
    public int deleteOrderOperateHistoryByIds(Long[] ids);

    /**
     * 删除订单操作历史记录信息
     * 
     * @param id 订单操作历史记录主键
     * @return 结果
     */
    public int deleteOrderOperateHistoryById(Long id);
}
