package com.ruoyi.member.service;

import java.util.List;
import com.ruoyi.member.domain.IntegrationChangeHistory;

/**
 * 积分变化历史记录Service接口
 * 
 * @author xuxing
 * @date 2021-08-22
 */
public interface IIntegrationChangeHistoryService 
{
    /**
     * 查询积分变化历史记录
     * 
     * @param id 积分变化历史记录主键
     * @return 积分变化历史记录
     */
    public IntegrationChangeHistory selectIntegrationChangeHistoryById(Long id);

    /**
     * 查询积分变化历史记录列表
     * 
     * @param integrationChangeHistory 积分变化历史记录
     * @return 积分变化历史记录集合
     */
    public List<IntegrationChangeHistory> selectIntegrationChangeHistoryList(IntegrationChangeHistory integrationChangeHistory);

    /**
     * 新增积分变化历史记录
     * 
     * @param integrationChangeHistory 积分变化历史记录
     * @return 结果
     */
    public int insertIntegrationChangeHistory(IntegrationChangeHistory integrationChangeHistory);

    /**
     * 修改积分变化历史记录
     * 
     * @param integrationChangeHistory 积分变化历史记录
     * @return 结果
     */
    public int updateIntegrationChangeHistory(IntegrationChangeHistory integrationChangeHistory);

    /**
     * 批量删除积分变化历史记录
     * 
     * @param ids 需要删除的积分变化历史记录主键集合
     * @return 结果
     */
    public int deleteIntegrationChangeHistoryByIds(Long[] ids);

    /**
     * 删除积分变化历史记录信息
     * 
     * @param id 积分变化历史记录主键
     * @return 结果
     */
    public int deleteIntegrationChangeHistoryById(Long id);
}
