package com.ruoyi.member.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.member.mapper.GrowthChangeHistoryMapper;
import com.ruoyi.member.domain.GrowthChangeHistory;
import com.ruoyi.member.service.IGrowthChangeHistoryService;

/**
 * 成长值变化历史记录Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-22
 */
@Service
public class GrowthChangeHistoryServiceImpl implements IGrowthChangeHistoryService 
{
    @Autowired
    private GrowthChangeHistoryMapper growthChangeHistoryMapper;

    /**
     * 查询成长值变化历史记录
     * 
     * @param id 成长值变化历史记录主键
     * @return 成长值变化历史记录
     */
    @Override
    public GrowthChangeHistory selectGrowthChangeHistoryById(Long id)
    {
        return growthChangeHistoryMapper.selectGrowthChangeHistoryById(id);
    }

    /**
     * 查询成长值变化历史记录列表
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 成长值变化历史记录
     */
    @Override
    public List<GrowthChangeHistory> selectGrowthChangeHistoryList(GrowthChangeHistory growthChangeHistory)
    {
        return growthChangeHistoryMapper.selectGrowthChangeHistoryList(growthChangeHistory);
    }

    /**
     * 新增成长值变化历史记录
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 结果
     */
    @Override
    public int insertGrowthChangeHistory(GrowthChangeHistory growthChangeHistory)
    {
        growthChangeHistory.setCreateTime(DateUtils.getNowDate());
        return growthChangeHistoryMapper.insertGrowthChangeHistory(growthChangeHistory);
    }

    /**
     * 修改成长值变化历史记录
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 结果
     */
    @Override
    public int updateGrowthChangeHistory(GrowthChangeHistory growthChangeHistory)
    {
        return growthChangeHistoryMapper.updateGrowthChangeHistory(growthChangeHistory);
    }

    /**
     * 批量删除成长值变化历史记录
     * 
     * @param ids 需要删除的成长值变化历史记录主键
     * @return 结果
     */
    @Override
    public int deleteGrowthChangeHistoryByIds(Long[] ids)
    {
        return growthChangeHistoryMapper.deleteGrowthChangeHistoryByIds(ids);
    }

    /**
     * 删除成长值变化历史记录信息
     * 
     * @param id 成长值变化历史记录主键
     * @return 结果
     */
    @Override
    public int deleteGrowthChangeHistoryById(Long id)
    {
        return growthChangeHistoryMapper.deleteGrowthChangeHistoryById(id);
    }
}
