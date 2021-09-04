package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.OrderReturnReason;

/**
 * 退货原因Service接口
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public interface IOrderReturnReasonService 
{
    /**
     * 查询退货原因
     * 
     * @param id 退货原因主键
     * @return 退货原因
     */
    public OrderReturnReason selectOrderReturnReasonById(Long id);

    /**
     * 查询退货原因列表
     * 
     * @param orderReturnReason 退货原因
     * @return 退货原因集合
     */
    public List<OrderReturnReason> selectOrderReturnReasonList(OrderReturnReason orderReturnReason);

    /**
     * 新增退货原因
     * 
     * @param orderReturnReason 退货原因
     * @return 结果
     */
    public int insertOrderReturnReason(OrderReturnReason orderReturnReason);

    /**
     * 修改退货原因
     * 
     * @param orderReturnReason 退货原因
     * @return 结果
     */
    public int updateOrderReturnReason(OrderReturnReason orderReturnReason);

    /**
     * 批量删除退货原因
     * 
     * @param ids 需要删除的退货原因主键集合
     * @return 结果
     */
    public int deleteOrderReturnReasonByIds(Long[] ids);

    /**
     * 删除退货原因信息
     * 
     * @param id 退货原因主键
     * @return 结果
     */
    public int deleteOrderReturnReasonById(Long id);
}
