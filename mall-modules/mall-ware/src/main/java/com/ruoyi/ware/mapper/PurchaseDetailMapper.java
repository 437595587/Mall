package com.ruoyi.ware.mapper;

import com.ruoyi.ware.domain.PurchaseDetail;

import java.util.List;

/**
 * 采购需求Mapper接口
 *
 * @author xuxing
 * @date 2021-08-23
 */
public interface PurchaseDetailMapper
{
    /**
     * 查询采购需求
     *
     * @param id 采购需求主键
     * @return 采购需求
     */
    public PurchaseDetail selectPurchaseDetailById(Long id);

    /**
     * 查询采购需求列表
     *
     * @param purchaseDetail 采购需求
     * @return 采购需求集合
     */
    public List<PurchaseDetail> selectPurchaseDetailList(PurchaseDetail purchaseDetail);

    /**
     * 新增采购需求
     *
     * @param purchaseDetail 采购需求
     * @return 结果
     */
    public int insertPurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 修改采购需求
     *
     * @param purchaseDetail 采购需求
     * @return 结果
     */
    public int updatePurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 删除采购需求
     *
     * @param id 采购需求主键
     * @return 结果
     */
    public int deletePurchaseDetailById(Long id);

    /**
     * 批量删除采购需求
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePurchaseDetailByIds(Long[] ids);
}
