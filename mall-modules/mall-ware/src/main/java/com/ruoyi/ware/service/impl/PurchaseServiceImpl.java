package com.ruoyi.ware.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.ware.domain.Purchase;
import com.ruoyi.ware.domain.PurchaseDetail;
import com.ruoyi.ware.domain.vo.MergeVo;
import com.ruoyi.ware.mapper.PurchaseDetailMapper;
import com.ruoyi.ware.mapper.PurchaseMapper;
import com.ruoyi.ware.service.IPurchaseService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 采购单Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class PurchaseServiceImpl implements IPurchaseService
{
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询采购单
     *
     * @param id 采购单主键
     * @return 采购单
     */
    @Override
    public Purchase selectPurchaseById(Long id)
    {
        return purchaseMapper.selectPurchaseById(id);
    }

    /**
     * 查询采购单列表
     *
     * @param purchase 采购单
     * @return 采购单
     */
    @Override
    public List<Purchase> selectPurchaseList(Purchase purchase)
    {
        return purchaseMapper.selectPurchaseList(purchase);
    }

    /**
     * 新增采购单
     *
     * @param purchase 采购单
     * @return 结果
     */
    @Override
    public int insertPurchase(Purchase purchase)
    {
        purchase.setCreateTime(DateUtils.getNowDate());
        return purchaseMapper.insertPurchase(purchase);
    }

    /**
     * 修改采购单
     *
     * @param purchase 采购单
     * @return 结果
     */
    @Override
    public int updatePurchase(Purchase purchase)
    {
        purchase.setUpdateTime(DateUtils.getNowDate());
        return purchaseMapper.updatePurchase(purchase);
    }

    /**
     * 批量删除采购单
     *
     * @param ids 需要删除的采购单主键
     * @return 结果
     */
    @Override
    public int deletePurchaseByIds(Long[] ids)
    {
        return purchaseMapper.deletePurchaseByIds(ids);
    }

    /**
     * 删除采购单信息
     *
     * @param id 采购单主键
     * @return 结果
     */
    @Override
    public int deletePurchaseById(Long id)
    {
        return purchaseMapper.deletePurchaseById(id);
    }

    @Override
    public List<Purchase> selectUnReceivePurchaseList() {
        return purchaseMapper.selectUnReceivePurchaseList();
    }

    @Override
    @Transactional
    public int mergePurchase(MergeVo mergeVo) {
        int result = 0;
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId == null) {
            Purchase purchase = new Purchase();
            purchase.setStatus(0L);
            purchase.setCreateTime(DateUtils.getNowDate());
            result += insertPurchase(purchase);
            purchaseId = purchase.getId();
        }
        List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetail> collect = items.stream().map(i -> {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setId(i);
            purchaseDetail.setPurchaseId(finalPurchaseId);
            purchaseDetail.setStatus(1L);
            return purchaseDetail;
        }).collect(Collectors.toList());
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        PurchaseDetailMapper purchaseDetailMapper = sqlSession.getMapper(PurchaseDetailMapper.class);
        for (PurchaseDetail purchaseDetail : collect) {
            result += purchaseDetailMapper.updatePurchaseDetail(purchaseDetail);
        }
        sqlSession.flushStatements();

        Purchase purchase = new Purchase();
        purchase.setId(purchaseId);
        purchase.setUpdateTime(DateUtils.getNowDate());
        result += updatePurchase(purchase);
        return result;
    }
}
