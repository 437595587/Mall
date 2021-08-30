package com.ruoyi.coupon.mapper;

import java.util.List;
import com.ruoyi.coupon.domain.CouponSpuCategoryRelation;

/**
 * 优惠券分类关联Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public interface CouponSpuCategoryRelationMapper 
{
    /**
     * 查询优惠券分类关联
     * 
     * @param id 优惠券分类关联主键
     * @return 优惠券分类关联
     */
    public CouponSpuCategoryRelation selectCouponSpuCategoryRelationById(Long id);

    /**
     * 查询优惠券分类关联列表
     * 
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 优惠券分类关联集合
     */
    public List<CouponSpuCategoryRelation> selectCouponSpuCategoryRelationList(CouponSpuCategoryRelation couponSpuCategoryRelation);

    /**
     * 新增优惠券分类关联
     * 
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 结果
     */
    public int insertCouponSpuCategoryRelation(CouponSpuCategoryRelation couponSpuCategoryRelation);

    /**
     * 修改优惠券分类关联
     * 
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 结果
     */
    public int updateCouponSpuCategoryRelation(CouponSpuCategoryRelation couponSpuCategoryRelation);

    /**
     * 删除优惠券分类关联
     * 
     * @param id 优惠券分类关联主键
     * @return 结果
     */
    public int deleteCouponSpuCategoryRelationById(Long id);

    /**
     * 批量删除优惠券分类关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCouponSpuCategoryRelationByIds(Long[] ids);
}
