package com.ruoyi.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.PmsCategoryBrandRelationMapper;
import com.ruoyi.product.domain.PmsCategoryBrandRelation;
import com.ruoyi.product.service.IPmsCategoryBrandRelationService;

/**
 * 品牌分类关联Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsCategoryBrandRelationServiceImpl implements IPmsCategoryBrandRelationService 
{
    @Autowired
    private PmsCategoryBrandRelationMapper pmsCategoryBrandRelationMapper;

    /**
     * 查询品牌分类关联
     * 
     * @param id 品牌分类关联主键
     * @return 品牌分类关联
     */
    @Override
    public PmsCategoryBrandRelation selectPmsCategoryBrandRelationById(Long id)
    {
        return pmsCategoryBrandRelationMapper.selectPmsCategoryBrandRelationById(id);
    }

    /**
     * 查询品牌分类关联列表
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 品牌分类关联
     */
    @Override
    public List<PmsCategoryBrandRelation> selectPmsCategoryBrandRelationList(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return pmsCategoryBrandRelationMapper.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
    }

    /**
     * 新增品牌分类关联
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 结果
     */
    @Override
    public int insertPmsCategoryBrandRelation(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return pmsCategoryBrandRelationMapper.insertPmsCategoryBrandRelation(pmsCategoryBrandRelation);
    }

    /**
     * 修改品牌分类关联
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 结果
     */
    @Override
    public int updatePmsCategoryBrandRelation(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return pmsCategoryBrandRelationMapper.updatePmsCategoryBrandRelation(pmsCategoryBrandRelation);
    }

    /**
     * 批量删除品牌分类关联
     * 
     * @param ids 需要删除的品牌分类关联主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryBrandRelationByIds(Long[] ids)
    {
        return pmsCategoryBrandRelationMapper.deletePmsCategoryBrandRelationByIds(ids);
    }

    /**
     * 删除品牌分类关联信息
     * 
     * @param id 品牌分类关联主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryBrandRelationById(Long id)
    {
        return pmsCategoryBrandRelationMapper.deletePmsCategoryBrandRelationById(id);
    }
}
