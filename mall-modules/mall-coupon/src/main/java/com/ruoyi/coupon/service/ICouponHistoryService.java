package com.ruoyi.coupon.service;

import java.util.List;
import com.ruoyi.coupon.domain.CouponHistory;

/**
 * 优惠券领取历史记录Service接口
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public interface ICouponHistoryService 
{
    /**
     * 查询优惠券领取历史记录
     * 
     * @param id 优惠券领取历史记录主键
     * @return 优惠券领取历史记录
     */
    public CouponHistory selectCouponHistoryById(Long id);

    /**
     * 查询优惠券领取历史记录列表
     * 
     * @param couponHistory 优惠券领取历史记录
     * @return 优惠券领取历史记录集合
     */
    public List<CouponHistory> selectCouponHistoryList(CouponHistory couponHistory);

    /**
     * 新增优惠券领取历史记录
     * 
     * @param couponHistory 优惠券领取历史记录
     * @return 结果
     */
    public int insertCouponHistory(CouponHistory couponHistory);

    /**
     * 修改优惠券领取历史记录
     * 
     * @param couponHistory 优惠券领取历史记录
     * @return 结果
     */
    public int updateCouponHistory(CouponHistory couponHistory);

    /**
     * 批量删除优惠券领取历史记录
     * 
     * @param ids 需要删除的优惠券领取历史记录主键集合
     * @return 结果
     */
    public int deleteCouponHistoryByIds(Long[] ids);

    /**
     * 删除优惠券领取历史记录信息
     * 
     * @param id 优惠券领取历史记录主键
     * @return 结果
     */
    public int deleteCouponHistoryById(Long id);
}
