package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.OrderReturnApply;

/**
 * 订单退货申请Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public interface OrderReturnApplyMapper 
{
    /**
     * 查询订单退货申请
     * 
     * @param id 订单退货申请主键
     * @return 订单退货申请
     */
    public OrderReturnApply selectOrderReturnApplyById(Long id);

    /**
     * 查询订单退货申请列表
     * 
     * @param orderReturnApply 订单退货申请
     * @return 订单退货申请集合
     */
    public List<OrderReturnApply> selectOrderReturnApplyList(OrderReturnApply orderReturnApply);

    /**
     * 新增订单退货申请
     * 
     * @param orderReturnApply 订单退货申请
     * @return 结果
     */
    public int insertOrderReturnApply(OrderReturnApply orderReturnApply);

    /**
     * 修改订单退货申请
     * 
     * @param orderReturnApply 订单退货申请
     * @return 结果
     */
    public int updateOrderReturnApply(OrderReturnApply orderReturnApply);

    /**
     * 删除订单退货申请
     * 
     * @param id 订单退货申请主键
     * @return 结果
     */
    public int deleteOrderReturnApplyById(Long id);

    /**
     * 批量删除订单退货申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderReturnApplyByIds(Long[] ids);
}
