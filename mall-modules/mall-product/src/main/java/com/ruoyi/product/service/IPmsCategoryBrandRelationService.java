package com.ruoyi.product.service;

import java.util.List;
import com.ruoyi.product.domain.PmsCategoryBrandRelation;

/**
 * 品牌分类关联Service接口
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public interface IPmsCategoryBrandRelationService 
{
    /**
     * 查询品牌分类关联
     * 
     * @param id 品牌分类关联主键
     * @return 品牌分类关联
     */
    public PmsCategoryBrandRelation selectPmsCategoryBrandRelationById(Long id);

    /**
     * 查询品牌分类关联列表
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 品牌分类关联集合
     */
    public List<PmsCategoryBrandRelation> selectPmsCategoryBrandRelationList(PmsCategoryBrandRelation pmsCategoryBrandRelation);

    /**
     * 新增品牌分类关联
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 结果
     */
    public int insertPmsCategoryBrandRelation(PmsCategoryBrandRelation pmsCategoryBrandRelation);

    /**
     * 修改品牌分类关联
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 结果
     */
    public int updatePmsCategoryBrandRelation(PmsCategoryBrandRelation pmsCategoryBrandRelation);

    /**
     * 批量删除品牌分类关联
     * 
     * @param ids 需要删除的品牌分类关联主键集合
     * @return 结果
     */
    public int deletePmsCategoryBrandRelationByIds(Long[] ids);

    /**
     * 删除品牌分类关联信息
     * 
     * @param id 品牌分类关联主键
     * @return 结果
     */
    public int deletePmsCategoryBrandRelationById(Long id);
}
