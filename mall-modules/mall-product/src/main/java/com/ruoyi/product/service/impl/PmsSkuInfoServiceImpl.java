package com.ruoyi.product.service.impl;

import com.ruoyi.product.domain.PmsSkuImages;
import com.ruoyi.product.domain.PmsSkuInfo;
import com.ruoyi.product.domain.PmsSpuInfoDesc;
import com.ruoyi.product.domain.vo.SkuItemVo;
import com.ruoyi.product.mapper.PmsSkuInfoMapper;
import com.ruoyi.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * sku信息Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsSkuInfoServiceImpl implements IPmsSkuInfoService
{
    @Autowired
    private PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    private IPmsSkuImagesService skuImagesService;

    @Autowired
    private IPmsSpuInfoDescService spuInfoDescService;

    @Autowired
    private IPmsAttrGroupService attrGroupService;

    @Autowired
    private IPmsSkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 查询sku信息
     *
     * @param skuId sku信息主键
     * @return sku信息
     */
    @Override
    public PmsSkuInfo selectPmsSkuInfoBySkuId(Long skuId)
    {
        return pmsSkuInfoMapper.selectPmsSkuInfoBySkuId(skuId);
    }

    /**
     * 查询sku信息列表
     *
     * @param pmsSkuInfo sku信息
     * @return sku信息
     */
    @Override
    public List<PmsSkuInfo> selectPmsSkuInfoList(PmsSkuInfo pmsSkuInfo)
    {
        return pmsSkuInfoMapper.selectPmsSkuInfoList(pmsSkuInfo);
    }

    /**
     * 新增sku信息
     *
     * @param pmsSkuInfo sku信息
     * @return 结果
     */
    @Override
    public int insertPmsSkuInfo(PmsSkuInfo pmsSkuInfo)
    {
        return pmsSkuInfoMapper.insertPmsSkuInfo(pmsSkuInfo);
    }

    /**
     * 修改sku信息
     *
     * @param pmsSkuInfo sku信息
     * @return 结果
     */
    @Override
    public int updatePmsSkuInfo(PmsSkuInfo pmsSkuInfo)
    {
        return pmsSkuInfoMapper.updatePmsSkuInfo(pmsSkuInfo);
    }

    /**
     * 批量删除sku信息
     *
     * @param skuIds 需要删除的sku信息主键
     * @return 结果
     */
    @Override
    public int deletePmsSkuInfoBySkuIds(Long[] skuIds)
    {
        return pmsSkuInfoMapper.deletePmsSkuInfoBySkuIds(skuIds);
    }

    /**
     * 删除sku信息信息
     *
     * @param skuId sku信息主键
     * @return 结果
     */
    @Override
    public int deletePmsSkuInfoBySkuId(Long skuId)
    {
        return pmsSkuInfoMapper.deletePmsSkuInfoBySkuId(skuId);
    }

    @Override
    public SkuItemVo item(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();
        CompletableFuture<PmsSkuInfo> infoFuture = CompletableFuture.supplyAsync(() -> {
            //1、sku基本信息
            PmsSkuInfo info = selectPmsSkuInfoBySkuId(skuId);
            skuItemVo.setInfo(info);
            return info;
        }, taskExecutor);
        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync(info -> {
            //3、spu销售属性组合
            Long spuId = info.getSpuId();
            List<SkuItemVo.ItemSaleAttrVo> saleAttr = skuSaleAttrValueService.selectSaleAttrsBySpuId(spuId);
            skuItemVo.setSaleAttr(saleAttr);
        }, taskExecutor);
        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync(info -> {
            //4、spu的介绍
            Long spuId = info.getSpuId();
            PmsSpuInfoDesc desc = spuInfoDescService.selectPmsSpuInfoDescBySpuId(spuId);
            skuItemVo.setDesc(desc);
        }, taskExecutor);
        CompletableFuture<Void> baseAttrFuture = infoFuture.thenAcceptAsync(info -> {
            //5、spu的规格参数信息
            Long spuId = info.getSpuId();
            Long catalogId = info.getCatalogId();
            List<SkuItemVo.SpuItemAttrGroupVo> groupAttrs = attrGroupService.selectPmsAttrGroupWithAttrsBySpuId(spuId, catalogId);
            skuItemVo.setGroupAttrs(groupAttrs);
        }, taskExecutor);
        CompletableFuture<Void> imagesFuture = CompletableFuture.runAsync(() -> {
            //2、sku图片信息
            PmsSkuImages skuImagesQuery = new PmsSkuImages();
            skuImagesQuery.setSkuId(skuId);
            List<PmsSkuImages> images = skuImagesService.selectPmsSkuImagesList(skuImagesQuery);
            skuItemVo.setImages(images);
        }, taskExecutor);
        //等待所有任务完成
        try {
            CompletableFuture.allOf(saleAttrFuture, descFuture, baseAttrFuture, imagesFuture).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return skuItemVo;
    }
}
