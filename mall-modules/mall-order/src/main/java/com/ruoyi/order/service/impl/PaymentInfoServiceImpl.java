package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.PaymentInfoMapper;
import com.ruoyi.order.domain.PaymentInfo;
import com.ruoyi.order.service.IPaymentInfoService;

/**
 * 支付信息Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-31
 */
@Service
public class PaymentInfoServiceImpl implements IPaymentInfoService 
{
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    /**
     * 查询支付信息
     * 
     * @param id 支付信息主键
     * @return 支付信息
     */
    @Override
    public PaymentInfo selectPaymentInfoById(Long id)
    {
        return paymentInfoMapper.selectPaymentInfoById(id);
    }

    /**
     * 查询支付信息列表
     * 
     * @param paymentInfo 支付信息
     * @return 支付信息
     */
    @Override
    public List<PaymentInfo> selectPaymentInfoList(PaymentInfo paymentInfo)
    {
        return paymentInfoMapper.selectPaymentInfoList(paymentInfo);
    }

    /**
     * 新增支付信息
     * 
     * @param paymentInfo 支付信息
     * @return 结果
     */
    @Override
    public int insertPaymentInfo(PaymentInfo paymentInfo)
    {
        paymentInfo.setCreateTime(DateUtils.getNowDate());
        return paymentInfoMapper.insertPaymentInfo(paymentInfo);
    }

    /**
     * 修改支付信息
     * 
     * @param paymentInfo 支付信息
     * @return 结果
     */
    @Override
    public int updatePaymentInfo(PaymentInfo paymentInfo)
    {
        return paymentInfoMapper.updatePaymentInfo(paymentInfo);
    }

    /**
     * 批量删除支付信息
     * 
     * @param ids 需要删除的支付信息主键
     * @return 结果
     */
    @Override
    public int deletePaymentInfoByIds(Long[] ids)
    {
        return paymentInfoMapper.deletePaymentInfoByIds(ids);
    }

    /**
     * 删除支付信息信息
     * 
     * @param id 支付信息主键
     * @return 结果
     */
    @Override
    public int deletePaymentInfoById(Long id)
    {
        return paymentInfoMapper.deletePaymentInfoById(id);
    }
}
