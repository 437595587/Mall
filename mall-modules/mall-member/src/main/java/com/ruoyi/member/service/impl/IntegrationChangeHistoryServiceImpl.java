package com.ruoyi.member.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.member.mapper.IntegrationChangeHistoryMapper;
import com.ruoyi.member.domain.IntegrationChangeHistory;
import com.ruoyi.member.service.IIntegrationChangeHistoryService;

/**
 * 积分变化历史记录Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-22
 */
@Service
public class IntegrationChangeHistoryServiceImpl implements IIntegrationChangeHistoryService 
{
    @Autowired
    private IntegrationChangeHistoryMapper integrationChangeHistoryMapper;

    /**
     * 查询积分变化历史记录
     * 
     * @param id 积分变化历史记录主键
     * @return 积分变化历史记录
     */
    @Override
    public IntegrationChangeHistory selectIntegrationChangeHistoryById(Long id)
    {
        return integrationChangeHistoryMapper.selectIntegrationChangeHistoryById(id);
    }

    /**
     * 查询积分变化历史记录列表
     * 
     * @param integrationChangeHistory 积分变化历史记录
     * @return 积分变化历史记录
     */
    @Override
    public List<IntegrationChangeHistory> selectIntegrationChangeHistoryList(IntegrationChangeHistory integrationChangeHistory)
    {
        return integrationChangeHistoryMapper.selectIntegrationChangeHistoryList(integrationChangeHistory);
    }

    /**
     * 新增积分变化历史记录
     * 
     * @param integrationChangeHistory 积分变化历史记录
     * @return 结果
     */
    @Override
    public int insertIntegrationChangeHistory(IntegrationChangeHistory integrationChangeHistory)
    {
        integrationChangeHistory.setCreateTime(DateUtils.getNowDate());
        return integrationChangeHistoryMapper.insertIntegrationChangeHistory(integrationChangeHistory);
    }

    /**
     * 修改积分变化历史记录
     * 
     * @param integrationChangeHistory 积分变化历史记录
     * @return 结果
     */
    @Override
    public int updateIntegrationChangeHistory(IntegrationChangeHistory integrationChangeHistory)
    {
        return integrationChangeHistoryMapper.updateIntegrationChangeHistory(integrationChangeHistory);
    }

    /**
     * 批量删除积分变化历史记录
     * 
     * @param ids 需要删除的积分变化历史记录主键
     * @return 结果
     */
    @Override
    public int deleteIntegrationChangeHistoryByIds(Long[] ids)
    {
        return integrationChangeHistoryMapper.deleteIntegrationChangeHistoryByIds(ids);
    }

    /**
     * 删除积分变化历史记录信息
     * 
     * @param id 积分变化历史记录主键
     * @return 结果
     */
    @Override
    public int deleteIntegrationChangeHistoryById(Long id)
    {
        return integrationChangeHistoryMapper.deleteIntegrationChangeHistoryById(id);
    }
}
