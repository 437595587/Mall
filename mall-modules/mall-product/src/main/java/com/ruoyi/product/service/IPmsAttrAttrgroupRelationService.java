package com.ruoyi.product.service;

import java.util.List;
import com.ruoyi.product.domain.PmsAttrAttrgroupRelation;

/**
 * 属性&属性分组关联Service接口
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public interface IPmsAttrAttrgroupRelationService 
{
    /**
     * 查询属性&属性分组关联
     * 
     * @param id 属性&属性分组关联主键
     * @return 属性&属性分组关联
     */
    public PmsAttrAttrgroupRelation selectPmsAttrAttrgroupRelationById(Long id);

    /**
     * 查询属性&属性分组关联列表
     * 
     * @param pmsAttrAttrgroupRelation 属性&属性分组关联
     * @return 属性&属性分组关联集合
     */
    public List<PmsAttrAttrgroupRelation> selectPmsAttrAttrgroupRelationList(PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation);

    /**
     * 新增属性&属性分组关联
     * 
     * @param pmsAttrAttrgroupRelation 属性&属性分组关联
     * @return 结果
     */
    public int insertPmsAttrAttrgroupRelation(PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation);

    /**
     * 修改属性&属性分组关联
     * 
     * @param pmsAttrAttrgroupRelation 属性&属性分组关联
     * @return 结果
     */
    public int updatePmsAttrAttrgroupRelation(PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation);

    /**
     * 批量删除属性&属性分组关联
     * 
     * @param ids 需要删除的属性&属性分组关联主键集合
     * @return 结果
     */
    public int deletePmsAttrAttrgroupRelationByIds(Long[] ids);

    /**
     * 删除属性&属性分组关联信息
     * 
     * @param id 属性&属性分组关联主键
     * @return 结果
     */
    public int deletePmsAttrAttrgroupRelationById(Long id);
}
