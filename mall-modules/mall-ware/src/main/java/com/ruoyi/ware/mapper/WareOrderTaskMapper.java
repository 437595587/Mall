package com.ruoyi.ware.mapper;

import com.ruoyi.ware.domain.WareOrderTask;

import java.util.List;

/**
 * 库存工作单Mapper接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface WareOrderTaskMapper
{
    /**
     * 查询库存工作单
     *
     * @param id 库存工作单主键
     * @return 库存工作单
     */
    public WareOrderTask selectWareOrderTaskById(Long id);

    /**
     * 查询库存工作单列表
     *
     * @param wareOrderTask 库存工作单
     * @return 库存工作单集合
     */
    public List<WareOrderTask> selectWareOrderTaskList(WareOrderTask wareOrderTask);

    /**
     * 新增库存工作单
     *
     * @param wareOrderTask 库存工作单
     * @return 结果
     */
    public int insertWareOrderTask(WareOrderTask wareOrderTask);

    /**
     * 修改库存工作单
     *
     * @param wareOrderTask 库存工作单
     * @return 结果
     */
    public int updateWareOrderTask(WareOrderTask wareOrderTask);

    /**
     * 删除库存工作单
     *
     * @param id 库存工作单主键
     * @return 结果
     */
    public int deleteWareOrderTaskById(Long id);

    /**
     * 批量删除库存工作单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWareOrderTaskByIds(Long[] ids);

    WareOrderTask selectOrderTaskByOrderSn(String orderSn);
}
