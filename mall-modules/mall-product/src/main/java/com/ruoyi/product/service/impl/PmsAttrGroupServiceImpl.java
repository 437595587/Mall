package com.ruoyi.product.service.impl;

import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.product.domain.PmsAttr;
import com.ruoyi.product.domain.PmsAttrAttrgroupRelation;
import com.ruoyi.product.domain.PmsAttrGroup;
import com.ruoyi.product.domain.vo.PmsAttrGroupAttrRespVo;
import com.ruoyi.product.domain.vo.SkuItemVo;
import com.ruoyi.product.mapper.PmsAttrAttrgroupRelationMapper;
import com.ruoyi.product.mapper.PmsAttrGroupMapper;
import com.ruoyi.product.mapper.PmsAttrMapper;
import com.ruoyi.product.service.IPmsAttrAttrgroupRelationService;
import com.ruoyi.product.service.IPmsAttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * 属性分组Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsAttrGroupServiceImpl implements IPmsAttrGroupService
{
    @Autowired
    private PmsAttrGroupMapper pmsAttrGroupMapper;

    @Autowired
    private IPmsAttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    private PmsAttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    private PmsAttrMapper attrMapper;

    /**
     * 查询属性分组
     *
     * @param attrGroupId 属性分组主键
     * @return 属性分组
     */
    @Override
    public PmsAttrGroup selectPmsAttrGroupByAttrGroupId(Long attrGroupId)
    {
        return pmsAttrGroupMapper.selectPmsAttrGroupByAttrGroupId(attrGroupId);
    }

    /**
     * 查询属性分组列表
     *
     * @param pmsAttrGroup 属性分组
     * @return 属性分组
     */
    @Override
    public List<PmsAttrGroup> selectPmsAttrGroupList(PmsAttrGroup pmsAttrGroup)
    {
        return pmsAttrGroupMapper.selectPmsAttrGroupList(pmsAttrGroup);
    }

    /**
     * 新增属性分组
     *
     * @param pmsAttrGroup 属性分组
     * @return 结果
     */
    @Override
    public int insertPmsAttrGroup(PmsAttrGroup pmsAttrGroup)
    {
        return pmsAttrGroupMapper.insertPmsAttrGroup(pmsAttrGroup);
    }

    /**
     * 修改属性分组
     *
     * @param pmsAttrGroup 属性分组
     * @return 结果
     */
    @Override
    public int updatePmsAttrGroup(PmsAttrGroup pmsAttrGroup)
    {
        return pmsAttrGroupMapper.updatePmsAttrGroup(pmsAttrGroup);
    }

    /**
     * 批量删除属性分组
     *
     * @param attrGroupIds 需要删除的属性分组主键
     * @return 结果
     */
    @Override
    public int deletePmsAttrGroupByAttrGroupIds(Long[] attrGroupIds)
    {
        int count = 0;
        for (Long attrGroupId : attrGroupIds) {
            PmsAttrAttrgroupRelation attrAttrgroupRelation = new PmsAttrAttrgroupRelation();
            attrAttrgroupRelation.setAttrGroupId(attrGroupId);
            List<PmsAttrAttrgroupRelation> attrAttrgroupRelations = attrAttrgroupRelationService.selectPmsAttrAttrgroupRelationList(attrAttrgroupRelation);
            count += attrAttrgroupRelations.size();
        }
        if (count > 0) {
            return -1;
        } else {
            return pmsAttrGroupMapper.deletePmsAttrGroupByAttrGroupIds(attrGroupIds);
        }
    }

    /**
     * 删除属性分组信息
     *
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    @Override
    public int deletePmsAttrGroupByAttrGroupId(Long attrGroupId)
    {
        PmsAttrAttrgroupRelation attrAttrgroupRelation = new PmsAttrAttrgroupRelation();
        attrAttrgroupRelation.setAttrGroupId(attrGroupId);
        List<PmsAttrAttrgroupRelation> attrAttrgroupRelations = attrAttrgroupRelationService.selectPmsAttrAttrgroupRelationList(attrAttrgroupRelation);
        if (attrAttrgroupRelations.size() > 0) {
            return -1;
        } else {
            return pmsAttrGroupMapper.deletePmsAttrGroupByAttrGroupId(attrGroupId);
        }
    }

    /**
     * 查询属性分组关联属性列表
     *
     * @param attrGroupId 属性分组主键
     * @param pmsAttr 属性条件
     * @return 结果
     */
    @Override
    public List<PmsAttr> selectPmsAttrListByGroupId(Long attrGroupId, PmsAttr pmsAttr) {
        return pmsAttrGroupMapper.selectPmsAttrListByGroupId(attrGroupId, pmsAttr);
    }

    /**
     * 删除属性分组关联属性
     * @param attrIds 属性主键列表
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePmsAttrAttrGroupByGroupId(Long[] attrIds) {
        return attrAttrgroupRelationMapper.deletePmsAttrAttrgroupRelationByAttrIds(attrIds);
    }

    /**
     * 查询未分组的属性
     * @param pmsAttr     属性条件
     * @return 结果
     */
    @Override
    public List<PmsAttr> selectPmsAttrNoRelation(PmsAttr pmsAttr) {
        return pmsAttrGroupMapper.selectPmsAttrNoRelation(pmsAttr);
    }


    /**
     * 添加属性分组关联属性
     *
     * @param attrIds 属性主键列表
     * @return 结果
     */
    @Override
    @Transactional
    public int addPmsAttrAttrRelation(Long attrGroupId, Long[] attrIds) {
        List<PmsAttrAttrgroupRelation> list = new LinkedList<>();
        for (Long attrId : attrIds) {
            PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = new PmsAttrAttrgroupRelation();
            pmsAttrAttrgroupRelation.setAttrId(attrId);
            pmsAttrAttrgroupRelation.setAttrGroupId(attrGroupId);
            list.add(pmsAttrAttrgroupRelation);
        }
        int result = 0;
        for (PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation : list) {
            result += attrAttrgroupRelationService.insertPmsAttrAttrgroupRelation(pmsAttrAttrgroupRelation);
        }
        return result;
    }

    /**
     * 根据分类id获取属性分组和关联属性
     * @param catalogId 分类id
     * @return 结果
     */
    @Override
    public List<PmsAttrGroupAttrRespVo> selectAttrGroupAttrsList(Long catalogId) {
        PmsAttrGroup pmsAttrGroup = new PmsAttrGroup();
        pmsAttrGroup.setCatelogId(catalogId);
        List<PmsAttrGroup> pmsAttrGroups = pmsAttrGroupMapper.selectPmsAttrGroupList(pmsAttrGroup);
        List<PmsAttrGroupAttrRespVo> list = new LinkedList<>();
        for (PmsAttrGroup attrGroup : pmsAttrGroups) {
            PmsAttrGroupAttrRespVo pmsAttrGroupAttrRespVo = new PmsAttrGroupAttrRespVo();
            BeanUtils.copyBeanProp(pmsAttrGroupAttrRespVo, attrGroup);
            Long attrGroupId = attrGroup.getAttrGroupId();
            List<PmsAttr> attrs = pmsAttrGroupMapper.selectPmsAttrListByGroupId(attrGroupId, null);
            pmsAttrGroupAttrRespVo.setAttrs(attrs);
            list.add(pmsAttrGroupAttrRespVo);
        }
        return list;
    }

    @Override
    public List<SkuItemVo.SpuItemAttrGroupVo> selectPmsAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        //查出当前spu对应的所有属性的分组信息和当前分组下的所有属性对应的值
        return pmsAttrGroupMapper.selectPmsAttrGroupWithAttrsBySpuId(spuId, catalogId);
    }
}
