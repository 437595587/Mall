package com.ruoyi.product.service;

import com.ruoyi.product.domain.PmsAttr;
import com.ruoyi.product.domain.vo.PmsAttrReqVo;
import com.ruoyi.product.domain.vo.PmsAttrRespVo;

import java.util.List;

/**
 * 商品属性Service接口
 *
 * @author xuxing
 * @date 2021-08-17
 */
public interface IPmsAttrService
{
    /**
     * 查询商品属性
     *
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    public PmsAttrRespVo selectPmsAttrByAttrId(Long attrId);

    /**
     * 查询商品属性列表
     *
     * @param pmsAttr 商品属性
     * @return 商品属性集合
     */
    public List<PmsAttr> selectPmsAttrList(PmsAttr pmsAttr);

    /**
     * 新增商品属性
     *
     * @param pmsAttrReqVo 商品属性
     * @return 结果
     */
    public int insertPmsAttr(PmsAttrReqVo pmsAttrReqVo);

    /**
     * 修改商品属性
     *
     * @param pmsAttrReqVo 商品属性
     * @return 结果
     */
    public int updatePmsAttr(PmsAttrReqVo pmsAttrReqVo);

    /**
     * 批量删除商品属性
     *
     * @param attrIds 需要删除的商品属性主键集合
     * @return 结果
     */
    public int deletePmsAttrByAttrIds(Long[] attrIds);

    /**
     * 删除商品属性信息
     *
     * @param attrId 商品属性主键
     * @return 结果
     */
    public int deletePmsAttrByAttrId(Long attrId);
}
