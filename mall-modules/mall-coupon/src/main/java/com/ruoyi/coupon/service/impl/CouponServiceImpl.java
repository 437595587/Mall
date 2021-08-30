package com.ruoyi.coupon.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.coupon.mapper.CouponMapper;
import com.ruoyi.coupon.domain.Coupon;
import com.ruoyi.coupon.service.ICouponService;

/**
 * 优惠券信息Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class CouponServiceImpl implements ICouponService 
{
    @Autowired
    private CouponMapper couponMapper;

    /**
     * 查询优惠券信息
     * 
     * @param id 优惠券信息主键
     * @return 优惠券信息
     */
    @Override
    public Coupon selectCouponById(Long id)
    {
        return couponMapper.selectCouponById(id);
    }

    /**
     * 查询优惠券信息列表
     * 
     * @param coupon 优惠券信息
     * @return 优惠券信息
     */
    @Override
    public List<Coupon> selectCouponList(Coupon coupon)
    {
        return couponMapper.selectCouponList(coupon);
    }

    /**
     * 新增优惠券信息
     * 
     * @param coupon 优惠券信息
     * @return 结果
     */
    @Override
    public int insertCoupon(Coupon coupon)
    {
        return couponMapper.insertCoupon(coupon);
    }

    /**
     * 修改优惠券信息
     * 
     * @param coupon 优惠券信息
     * @return 结果
     */
    @Override
    public int updateCoupon(Coupon coupon)
    {
        return couponMapper.updateCoupon(coupon);
    }

    /**
     * 批量删除优惠券信息
     * 
     * @param ids 需要删除的优惠券信息主键
     * @return 结果
     */
    @Override
    public int deleteCouponByIds(Long[] ids)
    {
        return couponMapper.deleteCouponByIds(ids);
    }

    /**
     * 删除优惠券信息信息
     * 
     * @param id 优惠券信息主键
     * @return 结果
     */
    @Override
    public int deleteCouponById(Long id)
    {
        return couponMapper.deleteCouponById(id);
    }
}
