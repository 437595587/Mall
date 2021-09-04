package com.ruoyi.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.OrderSettingMapper;
import com.ruoyi.order.domain.OrderSetting;
import com.ruoyi.order.service.IOrderSettingService;

/**
 * 订单配置信息Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-31
 */
@Service
public class OrderSettingServiceImpl implements IOrderSettingService 
{
    @Autowired
    private OrderSettingMapper orderSettingMapper;

    /**
     * 查询订单配置信息
     * 
     * @param id 订单配置信息主键
     * @return 订单配置信息
     */
    @Override
    public OrderSetting selectOrderSettingById(Long id)
    {
        return orderSettingMapper.selectOrderSettingById(id);
    }

    /**
     * 查询订单配置信息列表
     * 
     * @param orderSetting 订单配置信息
     * @return 订单配置信息
     */
    @Override
    public List<OrderSetting> selectOrderSettingList(OrderSetting orderSetting)
    {
        return orderSettingMapper.selectOrderSettingList(orderSetting);
    }

    /**
     * 新增订单配置信息
     * 
     * @param orderSetting 订单配置信息
     * @return 结果
     */
    @Override
    public int insertOrderSetting(OrderSetting orderSetting)
    {
        return orderSettingMapper.insertOrderSetting(orderSetting);
    }

    /**
     * 修改订单配置信息
     * 
     * @param orderSetting 订单配置信息
     * @return 结果
     */
    @Override
    public int updateOrderSetting(OrderSetting orderSetting)
    {
        return orderSettingMapper.updateOrderSetting(orderSetting);
    }

    /**
     * 批量删除订单配置信息
     * 
     * @param ids 需要删除的订单配置信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderSettingByIds(Long[] ids)
    {
        return orderSettingMapper.deleteOrderSettingByIds(ids);
    }

    /**
     * 删除订单配置信息信息
     * 
     * @param id 订单配置信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderSettingById(Long id)
    {
        return orderSettingMapper.deleteOrderSettingById(id);
    }
}
