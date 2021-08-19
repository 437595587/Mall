package com.ruoyi.product.service;

import java.util.List;
import com.ruoyi.product.domain.PmsSpuInfoDesc;

/**
 * spu信息介绍Service接口
 * 
 * @author xuxing
 * @date 2021-08-17
 */
public interface IPmsSpuInfoDescService 
{
    /**
     * 查询spu信息介绍
     * 
     * @param spuId spu信息介绍主键
     * @return spu信息介绍
     */
    public PmsSpuInfoDesc selectPmsSpuInfoDescBySpuId(Long spuId);

    /**
     * 查询spu信息介绍列表
     * 
     * @param pmsSpuInfoDesc spu信息介绍
     * @return spu信息介绍集合
     */
    public List<PmsSpuInfoDesc> selectPmsSpuInfoDescList(PmsSpuInfoDesc pmsSpuInfoDesc);

    /**
     * 新增spu信息介绍
     * 
     * @param pmsSpuInfoDesc spu信息介绍
     * @return 结果
     */
    public int insertPmsSpuInfoDesc(PmsSpuInfoDesc pmsSpuInfoDesc);

    /**
     * 修改spu信息介绍
     * 
     * @param pmsSpuInfoDesc spu信息介绍
     * @return 结果
     */
    public int updatePmsSpuInfoDesc(PmsSpuInfoDesc pmsSpuInfoDesc);

    /**
     * 批量删除spu信息介绍
     * 
     * @param spuIds 需要删除的spu信息介绍主键集合
     * @return 结果
     */
    public int deletePmsSpuInfoDescBySpuIds(Long[] spuIds);

    /**
     * 删除spu信息介绍信息
     * 
     * @param spuId spu信息介绍主键
     * @return 结果
     */
    public int deletePmsSpuInfoDescBySpuId(Long spuId);
}
