package com.ruoyi.coupon.mapper;

import java.util.List;
import com.ruoyi.coupon.domain.SeckillSession;

/**
 * 秒杀活动场次Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public interface SeckillSessionMapper 
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
     * 删除秒杀活动场次
     * 
     * @param id 秒杀活动场次主键
     * @return 结果
     */
    public int deleteSeckillSessionById(Long id);

    /**
     * 批量删除秒杀活动场次
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeckillSessionByIds(Long[] ids);
}
