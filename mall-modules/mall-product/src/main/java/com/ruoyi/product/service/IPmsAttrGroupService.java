package com.ruoyi.product.service;

import com.ruoyi.product.domain.PmsAttr;
import com.ruoyi.product.domain.PmsAttrGroup;
import com.ruoyi.product.domain.vo.PmsAttrGroupAttrRespVo;
import com.ruoyi.product.domain.vo.SkuItemVo;

import java.util.List;

/**
 * 属性分组Service接口
 *
 * @author xuxing
 * @date 2021-08-17
 */
public interface IPmsAttrGroupService
{
    /**
     * 查询属性分组
     *
     * @param attrGroupId 属性分组主键
     * @return 属性分组
     */
    public PmsAttrGroup selectPmsAttrGroupByAttrGroupId(Long attrGroupId);

    /**
     * 查询属性分组列表
     *
     * @param pmsAttrGroup 属性分组
     * @return 属性分组集合
     */
    public List<PmsAttrGroup> selectPmsAttrGroupList(PmsAttrGroup pmsAttrGroup);

    /**
     * 新增属性分组
     *
     * @param pmsAttrGroup 属性分组
     * @return 结果
     */
    public int insertPmsAttrGroup(PmsAttrGroup pmsAttrGroup);

    /**
     * 修改属性分组
     *
     * @param pmsAttrGroup 属性分组
     * @return 结果
     */
    public int updatePmsAttrGroup(PmsAttrGroup pmsAttrGroup);

    /**
     * 批量删除属性分组
     *
     * @param attrGroupIds 需要删除的属性分组主键集合
     * @return 结果
     */
    public int deletePmsAttrGroupByAttrGroupIds(Long[] attrGroupIds);

    /**
     * 删除属性分组信息
     *
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    public int deletePmsAttrGroupByAttrGroupId(Long attrGroupId);

    /**
     * 查询属性分组关联属性列表
     *
     * @param attrGroupId 属性分组主键
     * @param pmsAttr     属性条件
     * @return 结果
     */
    public List<PmsAttr> selectPmsAttrListByGroupId(Long attrGroupId, PmsAttr pmsAttr);

    /**
     * 删除属性分组关联属性
     * @param attrIds 属性主键列表
     * @return 结果
     */
    int deletePmsAttrAttrGroupByGroupId(Long[] attrIds);

    /**
     * 查询未分组的属性
     * @param pmsAttr     属性条件
     * @return 结果
     */
    List<PmsAttr> selectPmsAttrNoRelation(PmsAttr pmsAttr);

    /**
     * 添加属性分组关联属性
     *
     * @param attrIds 属性主键列表
     * @return 结果
     */
    int addPmsAttrAttrRelation(Long attrGroupId, Long[] attrIds);

    /**
     * 根据分类id获取属性分组和关联属性
     * @param catalogId 分类id
     * @return 结果
     */
    List<PmsAttrGroupAttrRespVo> selectAttrGroupAttrsList(Long catalogId);

    List<SkuItemVo.SpuItemAttrGroupVo> selectPmsAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}
