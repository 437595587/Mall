package com.ruoyi.ware.service.impl;

import java.util.List;

import com.ruoyi.ware.domain.WareOrderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ware.mapper.WareOrderTaskDetailMapper;
import com.ruoyi.ware.domain.WareOrderTaskDetail;
import com.ruoyi.ware.service.IWareOrderTaskDetailService;

/**
 * 库存工作单Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class WareOrderTaskDetailServiceImpl implements IWareOrderTaskDetailService
{
    @Autowired
    private WareOrderTaskDetailMapper wareOrderTaskDetailMapper;

    /**
     * 查询库存工作单
     *
     * @param id 库存工作单主键
     * @return 库存工作单
     */
    @Override
    public WareOrderTaskDetail selectWareOrderTaskDetailById(Long id)
    {
        return wareOrderTaskDetailMapper.selectWareOrderTaskDetailById(id);
    }

    /**
     * 查询库存工作单列表
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 库存工作单
     */
    @Override
    public List<WareOrderTaskDetail> selectWareOrderTaskDetailList(WareOrderTaskDetail wareOrderTaskDetail)
    {
        return wareOrderTaskDetailMapper.selectWareOrderTaskDetailList(wareOrderTaskDetail);
    }

    /**
     * 新增库存工作单
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 结果
     */
    @Override
    public int insertWareOrderTaskDetail(WareOrderTaskDetail wareOrderTaskDetail)
    {
        return wareOrderTaskDetailMapper.insertWareOrderTaskDetail(wareOrderTaskDetail);
    }

    /**
     * 修改库存工作单
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 结果
     */
    @Override
    public int updateWareOrderTaskDetail(WareOrderTaskDetail wareOrderTaskDetail)
    {
        return wareOrderTaskDetailMapper.updateWareOrderTaskDetail(wareOrderTaskDetail);
    }

    /**
     * 批量删除库存工作单
     *
     * @param ids 需要删除的库存工作单主键
     * @return 结果
     */
    @Override
    public int deleteWareOrderTaskDetailByIds(Long[] ids)
    {
        return wareOrderTaskDetailMapper.deleteWareOrderTaskDetailByIds(ids);
    }

    /**
     * 删除库存工作单信息
     *
     * @param id 库存工作单主键
     * @return 结果
     */
    @Override
    public int deleteWareOrderTaskDetailById(Long id)
    {
        return wareOrderTaskDetailMapper.deleteWareOrderTaskDetailById(id);
    }
}
