package com.ruoyi.product.service;

import com.ruoyi.product.domain.PmsCategory;
import com.ruoyi.product.domain.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 分类管理Service接口
 *
 * @author xuxing
 * @date 2021-08-18
 */
public interface IPmsCategoryService
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
     * 批量删除分类管理
     *
     * @param catIds 需要删除的分类管理主键集合
     * @return 结果
     */
    public int deletePmsCategoryByCatIds(Long[] catIds);

    /**
     * 删除分类管理信息
     *
     * @param catId 分类管理主键
     * @return 结果
     */
    public int deletePmsCategoryByCatId(Long catId);

    List<PmsCategory> selectLevel1Categorys();

    Map<String, List<Catelog2Vo>> selectCatalogJson();
}
