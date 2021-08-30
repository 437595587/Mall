package com.ruoyi.ware.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ware.mapper.WareOrderTaskMapper;
import com.ruoyi.ware.domain.WareOrderTask;
import com.ruoyi.ware.service.IWareOrderTaskService;

/**
 * 库存工作单Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class WareOrderTaskServiceImpl implements IWareOrderTaskService 
{
    @Autowired
    private WareOrderTaskMapper wareOrderTaskMapper;

    /**
     * 查询库存工作单
     * 
     * @param id 库存工作单主键
     * @return 库存工作单
     */
    @Override
    public WareOrderTask selectWareOrderTaskById(Long id)
    {
        return wareOrderTaskMapper.selectWareOrderTaskById(id);
    }

    /**
     * 查询库存工作单列表
     * 
     * @param wareOrderTask 库存工作单
     * @return 库存工作单
     */
    @Override
    public List<WareOrderTask> selectWareOrderTaskList(WareOrderTask wareOrderTask)
    {
        return wareOrderTaskMapper.selectWareOrderTaskList(wareOrderTask);
    }

    /**
     * 新增库存工作单
     * 
     * @param wareOrderTask 库存工作单
     * @return 结果
     */
    @Override
    public int insertWareOrderTask(WareOrderTask wareOrderTask)
    {
        wareOrderTask.setCreateTime(DateUtils.getNowDate());
        return wareOrderTaskMapper.insertWareOrderTask(wareOrderTask);
    }

    /**
     * 修改库存工作单
     * 
     * @param wareOrderTask 库存工作单
     * @return 结果
     */
    @Override
    public int updateWareOrderTask(WareOrderTask wareOrderTask)
    {
        return wareOrderTaskMapper.updateWareOrderTask(wareOrderTask);
    }

    /**
     * 批量删除库存工作单
     * 
     * @param ids 需要删除的库存工作单主键
     * @return 结果
     */
    @Override
    public int deleteWareOrderTaskByIds(Long[] ids)
    {
        return wareOrderTaskMapper.deleteWareOrderTaskByIds(ids);
    }

    /**
     * 删除库存工作单信息
     * 
     * @param id 库存工作单主键
     * @return 结果
     */
    @Override
    public int deleteWareOrderTaskById(Long id)
    {
        return wareOrderTaskMapper.deleteWareOrderTaskById(id);
    }
}
