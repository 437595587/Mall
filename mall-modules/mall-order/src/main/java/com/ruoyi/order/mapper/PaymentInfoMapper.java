package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.PaymentInfo;

/**
 * 支付信息Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-31
 */
public interface PaymentInfoMapper 
{
    /**
     * 查询支付信息
     * 
     * @param id 支付信息主键
     * @return 支付信息
     */
    public PaymentInfo selectPaymentInfoById(Long id);

    /**
     * 查询支付信息列表
     * 
     * @param paymentInfo 支付信息
     * @return 支付信息集合
     */
    public List<PaymentInfo> selectPaymentInfoList(PaymentInfo paymentInfo);

    /**
     * 新增支付信息
     * 
     * @param paymentInfo 支付信息
     * @return 结果
     */
    public int insertPaymentInfo(PaymentInfo paymentInfo);

    /**
     * 修改支付信息
     * 
     * @param paymentInfo 支付信息
     * @return 结果
     */
    public int updatePaymentInfo(PaymentInfo paymentInfo);

    /**
     * 删除支付信息
     * 
     * @param id 支付信息主键
     * @return 结果
     */
    public int deletePaymentInfoById(Long id);

    /**
     * 批量删除支付信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePaymentInfoByIds(Long[] ids);
}
