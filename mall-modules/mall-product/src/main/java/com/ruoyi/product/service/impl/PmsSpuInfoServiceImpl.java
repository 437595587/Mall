package com.ruoyi.product.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.PmsSpuInfoMapper;
import com.ruoyi.product.domain.PmsSpuInfo;
import com.ruoyi.product.service.IPmsSpuInfoService;

/**
 * spu信息Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsSpuInfoServiceImpl implements IPmsSpuInfoService 
{
    @Autowired
    private PmsSpuInfoMapper pmsSpuInfoMapper;

    /**
     * 查询spu信息
     * 
     * @param id spu信息主键
     * @return spu信息
     */
    @Override
    public PmsSpuInfo selectPmsSpuInfoById(Long id)
    {
        return pmsSpuInfoMapper.selectPmsSpuInfoById(id);
    }

    /**
     * 查询spu信息列表
     * 
     * @param pmsSpuInfo spu信息
     * @return spu信息
     */
    @Override
    public List<PmsSpuInfo> selectPmsSpuInfoList(PmsSpuInfo pmsSpuInfo)
    {
        return pmsSpuInfoMapper.selectPmsSpuInfoList(pmsSpuInfo);
    }

    /**
     * 新增spu信息
     * 
     * @param pmsSpuInfo spu信息
     * @return 结果
     */
    @Override
    public int insertPmsSpuInfo(PmsSpuInfo pmsSpuInfo)
    {
        pmsSpuInfo.setCreateTime(DateUtils.getNowDate());
        return pmsSpuInfoMapper.insertPmsSpuInfo(pmsSpuInfo);
    }

    /**
     * 修改spu信息
     * 
     * @param pmsSpuInfo spu信息
     * @return 结果
     */
    @Override
    public int updatePmsSpuInfo(PmsSpuInfo pmsSpuInfo)
    {
        pmsSpuInfo.setUpdateTime(DateUtils.getNowDate());
        return pmsSpuInfoMapper.updatePmsSpuInfo(pmsSpuInfo);
    }

    /**
     * 批量删除spu信息
     * 
     * @param ids 需要删除的spu信息主键
     * @return 结果
     */
    @Override
    public int deletePmsSpuInfoByIds(Long[] ids)
    {
        return pmsSpuInfoMapper.deletePmsSpuInfoByIds(ids);
    }

    /**
     * 删除spu信息信息
     * 
     * @param id spu信息主键
     * @return 结果
     */
    @Override
    public int deletePmsSpuInfoById(Long id)
    {
        return pmsSpuInfoMapper.deletePmsSpuInfoById(id);
    }
}
