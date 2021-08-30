package com.ruoyi.coupon.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.coupon.mapper.CouponSpuCategoryRelationMapper;
import com.ruoyi.coupon.domain.CouponSpuCategoryRelation;
import com.ruoyi.coupon.service.ICouponSpuCategoryRelationService;

/**
 * 优惠券分类关联Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class CouponSpuCategoryRelationServiceImpl implements ICouponSpuCategoryRelationService 
{
    @Autowired
    private CouponSpuCategoryRelationMapper couponSpuCategoryRelationMapper;

    /**
     * 查询优惠券分类关联
     * 
     * @param id 优惠券分类关联主键
     * @return 优惠券分类关联
     */
    @Override
    public CouponSpuCategoryRelation selectCouponSpuCategoryRelationById(Long id)
    {
        return couponSpuCategoryRelationMapper.selectCouponSpuCategoryRelationById(id);
    }

    /**
     * 查询优惠券分类关联列表
     * 
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 优惠券分类关联
     */
    @Override
    public List<CouponSpuCategoryRelation> selectCouponSpuCategoryRelationList(CouponSpuCategoryRelation couponSpuCategoryRelation)
    {
        return couponSpuCategoryRelationMapper.selectCouponSpuCategoryRelationList(couponSpuCategoryRelation);
    }

    /**
     * 新增优惠券分类关联
     * 
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 结果
     */
    @Override
    public int insertCouponSpuCategoryRelation(CouponSpuCategoryRelation couponSpuCategoryRelation)
    {
        return couponSpuCategoryRelationMapper.insertCouponSpuCategoryRelation(couponSpuCategoryRelation);
    }

    /**
     * 修改优惠券分类关联
     * 
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 结果
     */
    @Override
    public int updateCouponSpuCategoryRelation(CouponSpuCategoryRelation couponSpuCategoryRelation)
    {
        return couponSpuCategoryRelationMapper.updateCouponSpuCategoryRelation(couponSpuCategoryRelation);
    }

    /**
     * 批量删除优惠券分类关联
     * 
     * @param ids 需要删除的优惠券分类关联主键
     * @return 结果
     */
    @Override
    public int deleteCouponSpuCategoryRelationByIds(Long[] ids)
    {
        return couponSpuCategoryRelationMapper.deleteCouponSpuCategoryRelationByIds(ids);
    }

    /**
     * 删除优惠券分类关联信息
     * 
     * @param id 优惠券分类关联主键
     * @return 结果
     */
    @Override
    public int deleteCouponSpuCategoryRelationById(Long id)
    {
        return couponSpuCategoryRelationMapper.deleteCouponSpuCategoryRelationById(id);
    }
}
