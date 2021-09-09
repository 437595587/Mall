package com.ruoyi.coupon.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.coupon.domain.SeckillSession;
import com.ruoyi.coupon.domain.SeckillSkuRelation;
import com.ruoyi.coupon.mapper.SeckillSessionMapper;
import com.ruoyi.coupon.mapper.SeckillSkuRelationMapper;
import com.ruoyi.coupon.service.ISeckillSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 秒杀活动场次Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class SeckillSessionServiceImpl implements ISeckillSessionService
{
    @Autowired
    private SeckillSessionMapper seckillSessionMapper;

    @Autowired
    private SeckillSkuRelationMapper seckillSkuRelationMapper;

    /**
     * 查询秒杀活动场次
     *
     * @param id 秒杀活动场次主键
     * @return 秒杀活动场次
     */
    @Override
    public SeckillSession selectSeckillSessionById(Long id)
    {
        return seckillSessionMapper.selectSeckillSessionById(id);
    }

    /**
     * 查询秒杀活动场次列表
     *
     * @param seckillSession 秒杀活动场次
     * @return 秒杀活动场次
     */
    @Override
    public List<SeckillSession> selectSeckillSessionList(SeckillSession seckillSession)
    {
        return seckillSessionMapper.selectSeckillSessionList(seckillSession);
    }

    /**
     * 新增秒杀活动场次
     *
     * @param seckillSession 秒杀活动场次
     * @return 结果
     */
    @Override
    public int insertSeckillSession(SeckillSession seckillSession)
    {
        seckillSession.setCreateTime(DateUtils.getNowDate());
        return seckillSessionMapper.insertSeckillSession(seckillSession);
    }

    /**
     * 修改秒杀活动场次
     *
     * @param seckillSession 秒杀活动场次
     * @return 结果
     */
    @Override
    public int updateSeckillSession(SeckillSession seckillSession)
    {
        return seckillSessionMapper.updateSeckillSession(seckillSession);
    }

    /**
     * 批量删除秒杀活动场次
     *
     * @param ids 需要删除的秒杀活动场次主键
     * @return 结果
     */
    @Override
    public int deleteSeckillSessionByIds(Long[] ids)
    {
        return seckillSessionMapper.deleteSeckillSessionByIds(ids);
    }

    /**
     * 删除秒杀活动场次信息
     *
     * @param id 秒杀活动场次主键
     * @return 结果
     */
    @Override
    public int deleteSeckillSessionById(Long id)
    {
        return seckillSessionMapper.deleteSeckillSessionById(id);
    }

    @Override
    public List<SeckillSession> selectLatest3DaysSessionList() {
        String startTime = startTime();
        String endTime = endTime();
        List<SeckillSession> list = seckillSessionMapper.selectLatest3DaysSessionList(startTime, endTime);
        if (list != null && list.size() > 0) {
            return list.stream().map(session -> {
                Long id = session.getId();
                SeckillSkuRelation seckillSkuRelation = new SeckillSkuRelation();
                seckillSkuRelation.setPromotionSessionId(id);
                List<SeckillSkuRelation> relations = seckillSkuRelationMapper.selectSeckillSkuRelationList(seckillSkuRelation);
                session.setRelationSkus(relations);
                return session;
            }).collect(Collectors.toList());
        }
        return null;
    }

    private String startTime() {
        LocalDate now = LocalDate.now();
        LocalTime min = LocalTime.MIN;
        LocalDateTime start = LocalDateTime.of(now, min);
        String format = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }

    private String endTime() {
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusDays(2);
        LocalDateTime of = LocalDateTime.of(localDate, LocalTime.MAX);
        String format = of.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }
}
