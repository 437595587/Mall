package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.PmsBrand;

import java.util.List;

/**
 * 品牌Mapper接口
 *
 * @author xuxing
 * @date 2021-08-17
 */
public interface PmsBrandMapper
{
    /**
     * 查询品牌
     *
     * @param brandId 品牌主键
     * @return 品牌
     */
    public PmsBrand selectPmsBrandByBrandId(Long brandId);

    /**
     * 查询品牌列表
     *
     * @param pmsBrand 品牌
     * @return 品牌集合
     */
    public List<PmsBrand> selectPmsBrandList(PmsBrand pmsBrand);

    /**
     * 新增品牌
     *
     * @param pmsBrand 品牌
     * @return 结果
     */
    public int insertPmsBrand(PmsBrand pmsBrand);

    /**
     * 修改品牌
     *
     * @param pmsBrand 品牌
     * @return 结果
     */
    public int updatePmsBrand(PmsBrand pmsBrand);

    /**
     * 删除品牌
     *
     * @param brandId 品牌主键
     * @return 结果
     */
    public int deletePmsBrandByBrandId(Long brandId);

    /**
     * 批量删除品牌
     *
     * @param brandIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePmsBrandByBrandIds(Long[] brandIds);

    List<PmsBrand> selectPmsBrandByIds(List<Long> brandIds);
}
