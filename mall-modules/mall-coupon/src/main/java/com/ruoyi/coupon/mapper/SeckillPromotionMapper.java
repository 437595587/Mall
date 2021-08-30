package com.ruoyi.coupon.mapper;

import java.util.List;
import com.ruoyi.coupon.domain.SeckillPromotion;

/**
 * 秒杀活动Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public interface SeckillPromotionMapper 
{
    /**
     * 查询秒杀活动
     * 
     * @param id 秒杀活动主键
     * @return 秒杀活动
     */
    public SeckillPromotion selectSeckillPromotionById(Long id);

    /**
     * 查询秒杀活动列表
     * 
     * @param seckillPromotion 秒杀活动
     * @return 秒杀活动集合
     */
    public List<SeckillPromotion> selectSeckillPromotionList(SeckillPromotion seckillPromotion);

    /**
     * 新增秒杀活动
     * 
     * @param seckillPromotion 秒杀活动
     * @return 结果
     */
    public int insertSeckillPromotion(SeckillPromotion seckillPromotion);

    /**
     * 修改秒杀活动
     * 
     * @param seckillPromotion 秒杀活动
     * @return 结果
     */
    public int updateSeckillPromotion(SeckillPromotion seckillPromotion);

    /**
     * 删除秒杀活动
     * 
     * @param id 秒杀活动主键
     * @return 结果
     */
    public int deleteSeckillPromotionById(Long id);

    /**
     * 批量删除秒杀活动
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeckillPromotionByIds(Long[] ids);
}
