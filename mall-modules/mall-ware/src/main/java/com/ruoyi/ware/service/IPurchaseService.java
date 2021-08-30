package com.ruoyi.ware.service;

import com.ruoyi.ware.domain.Purchase;
import com.ruoyi.ware.domain.vo.MergeVo;

import java.util.List;

/**
 * 采购单Service接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface IPurchaseService
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
     * 批量删除采购单
     *
     * @param ids 需要删除的采购单主键集合
     * @return 结果
     */
    public int deletePurchaseByIds(Long[] ids);

    /**
     * 删除采购单信息
     *
     * @param id 采购单主键
     * @return 结果
     */
    public int deletePurchaseById(Long id);

    List<Purchase> selectUnReceivePurchaseList();

    int mergePurchase(MergeVo mergeVo);
}
