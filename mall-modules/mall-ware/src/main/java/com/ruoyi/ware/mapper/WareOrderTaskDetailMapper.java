package com.ruoyi.ware.mapper;

import com.ruoyi.ware.domain.WareOrderTask;
import com.ruoyi.ware.domain.WareOrderTaskDetail;

import java.util.List;

/**
 * 库存工作单Mapper接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface WareOrderTaskDetailMapper
{
    /**
     * 查询库存工作单
     *
     * @param id 库存工作单主键
     * @return 库存工作单
     */
    public WareOrderTaskDetail selectWareOrderTaskDetailById(Long id);

    /**
     * 查询库存工作单列表
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 库存工作单集合
     */
    public List<WareOrderTaskDetail> selectWareOrderTaskDetailList(WareOrderTaskDetail wareOrderTaskDetail);

    /**
     * 新增库存工作单
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 结果
     */
    public int insertWareOrderTaskDetail(WareOrderTaskDetail wareOrderTaskDetail);

    /**
     * 修改库存工作单
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 结果
     */
    public int updateWareOrderTaskDetail(WareOrderTaskDetail wareOrderTaskDetail);

    /**
     * 删除库存工作单
     *
     * @param id 库存工作单主键
     * @return 结果
     */
    public int deleteWareOrderTaskDetailById(Long id);

    /**
     * 批量删除库存工作单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWareOrderTaskDetailByIds(Long[] ids);
}
