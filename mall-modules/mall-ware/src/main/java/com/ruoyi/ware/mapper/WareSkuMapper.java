package com.ruoyi.ware.mapper;

import com.ruoyi.ware.domain.WareSku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存Mapper接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface WareSkuMapper {
    /**
     * 查询商品库存
     *
     * @param id 商品库存主键
     * @return 商品库存
     */
    public WareSku selectWareSkuById(Long id);

    /**
     * 查询商品库存列表
     *
     * @param wareSku 商品库存
     * @return 商品库存集合
     */
    public List<WareSku> selectWareSkuList(WareSku wareSku);

    /**
     * 新增商品库存
     *
     * @param wareSku 商品库存
     * @return 结果
     */
    public int insertWareSku(WareSku wareSku);

    /**
     * 修改商品库存
     *
     * @param wareSku 商品库存
     * @return 结果
     */
    public int updateWareSku(WareSku wareSku);

    /**
     * 删除商品库存
     *
     * @param id 商品库存主键
     * @return 结果
     */
    public int deleteWareSkuById(Long id);

    /**
     * 批量删除商品库存
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWareSkuByIds(Long[] ids);

    Long selectSkuHasStock(Long skuId);

    List<Long> listWareIdHasSkuStock(Long skuId);

    int lockSkuStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Long num);

    void unlockStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Long num, @Param("taskDetailId") Long taskDetailId);
}
