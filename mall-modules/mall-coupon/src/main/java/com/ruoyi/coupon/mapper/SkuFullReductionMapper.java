package com.ruoyi.coupon.mapper;

import java.util.List;
import com.ruoyi.coupon.domain.SkuFullReduction;

/**
 * 商品满减信息Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public interface SkuFullReductionMapper 
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
     * 删除商品满减信息
     * 
     * @param id 商品满减信息主键
     * @return 结果
     */
    public int deleteSkuFullReductionById(Long id);

    /**
     * 批量删除商品满减信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSkuFullReductionByIds(Long[] ids);
}
