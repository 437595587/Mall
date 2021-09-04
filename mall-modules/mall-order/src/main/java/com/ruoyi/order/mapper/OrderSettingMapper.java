package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.OrderSetting;

/**
 * 订单配置信息Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public interface OrderSettingMapper 
{
    /**
     * 查询订单配置信息
     * 
     * @param id 订单配置信息主键
     * @return 订单配置信息
     */
    public OrderSetting selectOrderSettingById(Long id);

    /**
     * 查询订单配置信息列表
     * 
     * @param orderSetting 订单配置信息
     * @return 订单配置信息集合
     */
    public List<OrderSetting> selectOrderSettingList(OrderSetting orderSetting);

    /**
     * 新增订单配置信息
     * 
     * @param orderSetting 订单配置信息
     * @return 结果
     */
    public int insertOrderSetting(OrderSetting orderSetting);

    /**
     * 修改订单配置信息
     * 
     * @param orderSetting 订单配置信息
     * @return 结果
     */
    public int updateOrderSetting(OrderSetting orderSetting);

    /**
     * 删除订单配置信息
     * 
     * @param id 订单配置信息主键
     * @return 结果
     */
    public int deleteOrderSettingById(Long id);

    /**
     * 批量删除订单配置信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderSettingByIds(Long[] ids);
}
