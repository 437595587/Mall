package com.ruoyi.coupon.mapper;

import java.util.List;
import com.ruoyi.coupon.domain.SpuBounds;

/**
 * 商品spu积分设置Mapper接口
 * 
 * @author xuxing
 * @date 2021-08-23
 */
public interface SpuBoundsMapper 
{
    /**
     * 查询商品spu积分设置
     * 
     * @param id 商品spu积分设置主键
     * @return 商品spu积分设置
     */
    public SpuBounds selectSpuBoundsById(Long id);

    /**
     * 查询商品spu积分设置列表
     * 
     * @param spuBounds 商品spu积分设置
     * @return 商品spu积分设置集合
     */
    public List<SpuBounds> selectSpuBoundsList(SpuBounds spuBounds);

    /**
     * 新增商品spu积分设置
     * 
     * @param spuBounds 商品spu积分设置
     * @return 结果
     */
    public int insertSpuBounds(SpuBounds spuBounds);

    /**
     * 修改商品spu积分设置
     * 
     * @param spuBounds 商品spu积分设置
     * @return 结果
     */
    public int updateSpuBounds(SpuBounds spuBounds);

    /**
     * 删除商品spu积分设置
     * 
     * @param id 商品spu积分设置主键
     * @return 结果
     */
    public int deleteSpuBoundsById(Long id);

    /**
     * 批量删除商品spu积分设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpuBoundsByIds(Long[] ids);
}
