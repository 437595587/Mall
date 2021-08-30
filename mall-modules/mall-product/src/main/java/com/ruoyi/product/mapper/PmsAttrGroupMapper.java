package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.PmsAttr;
import com.ruoyi.product.domain.PmsAttrGroup;
import com.ruoyi.product.domain.vo.SkuItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组Mapper接口
 *
 * @author xuxing
 * @date 2021-08-17
 */
public interface PmsAttrGroupMapper {
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
     * 删除属性分组
     *
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    public int deletePmsAttrGroupByAttrGroupId(Long attrGroupId);

    /**
     * 批量删除属性分组
     *
     * @param attrGroupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePmsAttrGroupByAttrGroupIds(Long[] attrGroupIds);

    /**
     * 查询属性分组关联属性列表
     *
     * @param attrGroupId 属性分组主键
     * @param pmsAttr     属性条件
     * @return 结果
     */
    public List<PmsAttr> selectPmsAttrListByGroupId(@Param("attrGroupId") Long attrGroupId, @Param("pmsAttr") PmsAttr pmsAttr);

    /**
     * 查询未分组的属性
     *
     * @param pmsAttr 属性条件
     * @return 结果
     */
    List<PmsAttr> selectPmsAttrNoRelation(PmsAttr pmsAttr);

    List<SkuItemVo.SpuItemAttrGroupVo> selectPmsAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
