package com.ruoyi.coupon.service;

import com.ruoyi.coupon.domain.SeckillSession;

import java.util.List;

/**
 * 秒杀活动场次Service接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface ISeckillSessionService
{
    /**
     * 查询秒杀活动场次
     *
     * @param id 秒杀活动场次主键
     * @return 秒杀活动场次
     */
    public SeckillSession selectSeckillSessionById(Long id);

    /**
     * 查询秒杀活动场次列表
     *
     * @param seckillSession 秒杀活动场次
     * @return 秒杀活动场次集合
     */
    public List<SeckillSession> selectSeckillSessionList(SeckillSession seckillSession);

    /**
     * 新增秒杀活动场次
     *
     * @param seckillSession 秒杀活动场次
     * @return 结果
     */
    public int insertSeckillSession(SeckillSession seckillSession);

    /**
     * 修改秒杀活动场次
     *
     * @param seckillSession 秒杀活动场次
     * @return 结果
     */
    public int updateSeckillSession(SeckillSession seckillSession);

    /**
     * 批量删除秒杀活动场次
     *
     * @param ids 需要删除的秒杀活动场次主键集合
     * @return 结果
     */
    public int deleteSeckillSessionByIds(Long[] ids);

    /**
     * 删除秒杀活动场次信息
     *
     * @param id 秒杀活动场次主键
     * @return 结果
     */
    public int deleteSeckillSessionById(Long id);

    List<SeckillSession> selectLatest3DaysSessionList();
}
