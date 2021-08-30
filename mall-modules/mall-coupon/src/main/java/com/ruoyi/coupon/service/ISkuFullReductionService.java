package com.ruoyi.coupon.service;

import com.ruoyi.coupon.domain.SkuFullReduction;
import com.ruoyi.mall.api.to.SkuReductionTo;

import java.util.List;

/**
 * 商品满减信息Service接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface ISkuFullReductionService
{
    /**
     * 查询商品满减信息
     *
     * @param id 商品满减信息主键
     * @return 商品满减信息
     */
    public SkuFullReduction selectSkuFullReductionById(Long id);

    /**
     * 查询商品满减信息列表
     *
     * @param skuFullReduction 商品满减信息
     * @return 商品满减信息集合
     */
    public List<SkuFullReduction> selectSkuFullReductionList(SkuFullReduction skuFullReduction);

    /**
     * 新增商品满减信息
     *
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */
    public int insertSkuFullReduction(SkuFullReduction skuFullReduction);

    /**
     * 修改商品满减信息
     *
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */
    public int updateSkuFullReduction(SkuFullReduction skuFullReduction);

    /**
     * 批量删除商品满减信息
     *
     * @param ids 需要删除的商品满减信息主键集合
     * @return 结果
     */
    public int deleteSkuFullReductionByIds(Long[] ids);

    /**
     * 删除商品满减信息信息
     *
     * @param id 商品满减信息主键
     * @return 结果
     */
    public int deleteSkuFullReductionById(Long id);

    /**
     * 保存商品的优惠信息
     */
    int addSkuSmsInfo(SkuReductionTo skuReductionTo);
}
