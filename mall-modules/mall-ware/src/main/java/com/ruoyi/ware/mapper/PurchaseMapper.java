package com.ruoyi.ware.mapper;

import com.ruoyi.ware.domain.Purchase;

import java.util.List;

/**
 * 采购单Mapper接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface PurchaseMapper
{
    /**
     * 查询采购单
     *
     * @param id 采购单主键
     * @return 采购单
     */
    public Purchase selectPurchaseById(Long id);

    /**
     * 查询采购单列表
     *
     * @param purchase 采购单
     * @return 采购单集合
     */
    public List<Purchase> selectPurchaseList(Purchase purchase);

    /**
     * 新增采购单
     *
     * @param purchase 采购单
     * @return 结果
     */
    public int insertPurchase(Purchase purchase);

    /**
     * 修改采购单
     *
     * @param purchase 采购单
     * @return 结果
     */
    public int updatePurchase(Purchase purchase);

    /**
     * 删除采购单
     *
     * @param id 采购单主键
     * @return 结果
     */
    public int deletePurchaseById(Long id);

    /**
     * 批量删除采购单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePurchaseByIds(Long[] ids);

    List<Purchase> selectUnReceivePurchaseList();
}
