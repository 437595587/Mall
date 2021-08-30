package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.PmsCategory;

import java.util.List;

/**
 * 分类管理Mapper接口
 *
 * @author xuxing
 * @date 2021-08-18
 */
public interface PmsCategoryMapper
{
    /**
     * 查询分类管理
     *
     * @param catId 分类管理主键
     * @return 分类管理
     */
    public PmsCategory selectPmsCategoryByCatId(Long catId);

    /**
     * 查询分类管理列表
     *
     * @param pmsCategory 分类管理
     * @return 分类管理集合
     */
    public List<PmsCategory> selectPmsCategoryList(PmsCategory pmsCategory);

    /**
     * 新增分类管理
     *
     * @param pmsCategory 分类管理
     * @return 结果
     */
    public int insertPmsCategory(PmsCategory pmsCategory);

    /**
     * 修改分类管理
     *
     * @param pmsCategory 分类管理
     * @return 结果
     */
    public int updatePmsCategory(PmsCategory pmsCategory);

    /**
     * 删除分类管理
     *
     * @param catId 分类管理主键
     * @return 结果
     */
    public int deletePmsCategoryByCatId(Long catId);

    /**
     * 批量删除分类管理
     *
     * @param catIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePmsCategoryByCatIds(Long[] catIds);

    List<PmsCategory> selectLevel1Categorys();
}
