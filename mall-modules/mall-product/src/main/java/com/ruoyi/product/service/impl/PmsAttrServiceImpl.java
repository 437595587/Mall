package com.ruoyi.product.service.impl;

import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.product.domain.PmsAttr;
import com.ruoyi.product.domain.PmsAttrAttrgroupRelation;
import com.ruoyi.product.domain.vo.PmsAttrReqVo;
import com.ruoyi.product.domain.vo.PmsAttrRespVo;
import com.ruoyi.product.mapper.PmsAttrMapper;
import com.ruoyi.product.service.IPmsAttrAttrgroupRelationService;
import com.ruoyi.product.service.IPmsAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsAttrServiceImpl implements IPmsAttrService
{
    @Autowired
    private PmsAttrMapper pmsAttrMapper;

    @Autowired
    private IPmsAttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 查询商品属性
     *
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    @Cacheable(value = "attr",key = "'attrInfo:' + #root.args[0]")
    @Override
    public PmsAttrRespVo selectPmsAttrByAttrId(Long attrId)
    {
        PmsAttr pmsAttr = pmsAttrMapper.selectPmsAttrByAttrId(attrId);
        PmsAttrRespVo pmsAttrRespVo = new PmsAttrRespVo();
        if (pmsAttr.getAttrType().equals(1)) {
            PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = new PmsAttrAttrgroupRelation();
            pmsAttrAttrgroupRelation.setAttrId(pmsAttr.getAttrId());
            List<PmsAttrAttrgroupRelation> attrAttrgroupRelations = attrAttrgroupRelationService.selectPmsAttrAttrgroupRelationList(pmsAttrAttrgroupRelation);
            if (!attrAttrgroupRelations.isEmpty()) {
                pmsAttrRespVo.setAttrGroupId(attrAttrgroupRelations.get(0).getAttrGroupId());
            }
        }
        BeanUtils.copyBeanProp(pmsAttrRespVo, pmsAttr);
        return pmsAttrRespVo;
    }

    /**
     * 查询商品属性列表
     *
     * @param pmsAttr 商品属性
     * @return 商品属性
     */
    @Override
    public List<PmsAttr> selectPmsAttrList(PmsAttr pmsAttr)
    {
        return pmsAttrMapper.selectPmsAttrList(pmsAttr);
    }

    /**
     * 新增商品属性
     *
     * @param pmsAttrReqVo 商品属性
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPmsAttr(PmsAttrReqVo pmsAttrReqVo)
    {
        int result = 0;
        Integer attrType = pmsAttrReqVo.getAttrType();
        PmsAttr pmsAttr = new PmsAttr();
        BeanUtils.copyBeanProp(pmsAttr, pmsAttrReqVo);
        result += pmsAttrMapper.insertPmsAttr(pmsAttr);
        //如果是规格参数，添加分组信息
        if (attrType.equals(1) && pmsAttrReqVo.getAttrGroupId() != null) {
            PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = new PmsAttrAttrgroupRelation();
            pmsAttrAttrgroupRelation.setAttrId(pmsAttr.getAttrId());
            pmsAttrAttrgroupRelation.setAttrGroupId(pmsAttrReqVo.getAttrGroupId());
            result += attrAttrgroupRelationService.insertPmsAttrAttrgroupRelation(pmsAttrAttrgroupRelation);
        }
        return result;
    }

    /**
     * 修改商品属性
     *
     * @param pmsAttrReqVo 商品属性
     * @return 结果
     */
    @CacheEvict(value = "attr",key = "'attrInfo:' + #root.args[0].attrId")
    @Override
    @Transactional
    public int updatePmsAttr(PmsAttrReqVo pmsAttrReqVo)
    {
        int result = 0;
        //更新属性信息
        PmsAttr pmsAttr = new PmsAttr();
        BeanUtils.copyBeanProp(pmsAttr, pmsAttrReqVo);
        result += pmsAttrMapper.updatePmsAttr(pmsAttr);
        Integer attrType = pmsAttr.getAttrType();
        //如果是规格参数，更新属性分组和属性关联表信息
        if (attrType.equals(1)) {
            Long attrGroupId = pmsAttrReqVo.getAttrGroupId();
            PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = new PmsAttrAttrgroupRelation();
            pmsAttrAttrgroupRelation.setAttrId(pmsAttr.getAttrId());
            //获取属性分组和属性关联表信息
            PmsAttrAttrgroupRelation dbPmsAttrAttrgroupRelation = attrAttrgroupRelationService.selectPmsAttrAttrgroupRelationList(pmsAttrAttrgroupRelation).get(0);
            Long dbAttrGroupId = dbPmsAttrAttrgroupRelation.getAttrGroupId();
            Long id = dbPmsAttrAttrgroupRelation.getId();
            //属性分组和属性关联表的属性分组id与要更新的属性分组id是否相同，如果不同，删除当前关系，添加新关系
            if (!dbAttrGroupId.equals(attrGroupId)) {
                result += attrAttrgroupRelationService.deletePmsAttrAttrgroupRelationById(id);
                if (attrGroupId != null) {
                    PmsAttrAttrgroupRelation iPmsAttrAttrgroupRelation = new PmsAttrAttrgroupRelation();
                    iPmsAttrAttrgroupRelation.setAttrId(pmsAttr.getAttrId());
                    iPmsAttrAttrgroupRelation.setAttrGroupId(attrGroupId);
                    result += attrAttrgroupRelationService.insertPmsAttrAttrgroupRelation(iPmsAttrAttrgroupRelation);
                }
            }
        }
        return result;
    }

    /**
     * 批量删除商品属性
     *
     * @param attrIds 需要删除的商品属性主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePmsAttrByAttrIds(Long[] attrIds)
    {
        int result = 0;
        result += pmsAttrMapper.deletePmsAttrByAttrIds(attrIds);
        result += attrAttrgroupRelationService.deletePmsAttrAttrgroupRelationByAttrIds(attrIds);
        return result;
    }

    /**
     * 删除商品属性信息
     *
     * @param attrId 商品属性主键
     * @return 结果
     */
    @Override
    public int deletePmsAttrByAttrId(Long attrId)
    {
        return pmsAttrMapper.deletePmsAttrByAttrId(attrId);
    }
}
