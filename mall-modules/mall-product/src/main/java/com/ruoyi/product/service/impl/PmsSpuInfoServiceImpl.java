package com.ruoyi.product.service.impl;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.mall.api.RemoteCouponService;
import com.ruoyi.mall.api.RemoteSearchService;
import com.ruoyi.mall.api.RemoteWareService;
import com.ruoyi.mall.api.to.SkuReductionTo;
import com.ruoyi.mall.api.to.SpuBoundTo;
import com.ruoyi.mall.api.to.es.SkuEsModel;
import com.ruoyi.mall.api.vo.SkuHasStockVo;
import com.ruoyi.product.domain.*;
import com.ruoyi.product.domain.vo.spuSaveVo.*;
import com.ruoyi.product.mapper.*;
import com.ruoyi.product.service.IPmsSpuInfoService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * spu信息Service业务层处理
 *
 * @author xuxing
 * @date 2021-08-17
 */
@Service
public class PmsSpuInfoServiceImpl implements IPmsSpuInfoService
{
    protected static final Logger log = LoggerFactory.getLogger(PmsSpuInfoServiceImpl.class);

    @Autowired
    private PmsSpuInfoMapper pmsSpuInfoMapper;

    @Autowired
    private PmsSpuInfoDescMapper pmsSpuInfoDescMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private PmsAttrMapper pmsAttrMapper;

    @Autowired
    private PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    private RemoteCouponService remoteCouponService;

    @Autowired
    PmsBrandMapper pmsBrandMapper;

    @Autowired
    PmsCategoryMapper pmsCategoryMapper;

    @Autowired
    PmsProductAttrValueMapper pmsProductAttrValueMapper;

    @Autowired
    RemoteWareService remoteWareService;

    @Autowired
    RemoteSearchService remoteSearchService;

    /**
     * 查询spu信息
     *
     * @param id spu信息主键
     * @return spu信息
     */
    @Override
    public PmsSpuInfo selectPmsSpuInfoById(Long id)
    {
        return pmsSpuInfoMapper.selectPmsSpuInfoById(id);
    }

    /**
     * 查询spu信息列表
     *
     * @param pmsSpuInfo spu信息
     * @return spu信息
     */
    @Override
    public List<PmsSpuInfo> selectPmsSpuInfoList(PmsSpuInfo pmsSpuInfo)
    {
        return pmsSpuInfoMapper.selectPmsSpuInfoList(pmsSpuInfo);
    }

    /**
     * 新增spu信息
     *
     * @param spuSaveVo spu保存
     * @return 结果
     */
    //seata AT 分布式事务
    @Transactional
    @Override
    public int insertPmsSpuInfo(SpuSaveVo spuSaveVo)
    {
        int result = 0;
        //1. 保存spu基本信息 pms_spu_info
        PmsSpuInfo pmsSpuInfo = new PmsSpuInfo();
        BeanUtils.copyBeanProp(pmsSpuInfo, spuSaveVo);
        pmsSpuInfo.setCreateTime(DateUtils.getNowDate());
        result += pmsSpuInfoMapper.insertPmsSpuInfo(pmsSpuInfo);
        Long spuId = pmsSpuInfo.getId();
        //2. 保存spu的描述图片 pms_spu_info_desc
        List<String> decript = spuSaveVo.getDecript();
        if (decript != null && decript.size() > 0) {
            PmsSpuInfoDesc pmsSpuInfoDesc = new PmsSpuInfoDesc();
            pmsSpuInfoDesc.setSpuId(spuId);
            pmsSpuInfoDesc.setDecript(String.join(",", decript));
            result += pmsSpuInfoDescMapper.insertPmsSpuInfoDesc(pmsSpuInfoDesc);
        }
        //TODO 批量保存后续优化
        //批量保存sqlSession
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        //3. 保存spu的图集 pms_spu_images
        List<String> images = spuSaveVo.getImages();
        if (images != null && images.size() > 0) {
            PmsSpuImagesMapper pmsSpuImagesMapper = sqlSession.getMapper(PmsSpuImagesMapper.class);
            for (String image : images) {
                PmsSpuImages pmsSpuImages = new PmsSpuImages();
                pmsSpuImages.setSpuId(spuId);
                pmsSpuImages.setImgUrl(image);
                result += pmsSpuImagesMapper.insertPmsSpuImages(pmsSpuImages);
            }
            sqlSession.flushStatements();
        }
        //4. 保存spu的规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        if (baseAttrs != null && baseAttrs.size() > 0) {
            List<PmsProductAttrValue> pmsProductAttrValues = baseAttrs.stream()
                    .filter(attr -> StringUtils.isNotEmpty(attr.getAttrValues()))
                    .map(attr -> {
                PmsProductAttrValue pmsProductAttrValue = new PmsProductAttrValue();
                pmsProductAttrValue.setAttrId(attr.getAttrId());
                PmsAttr pmsAttr = pmsAttrMapper.selectPmsAttrByAttrId(attr.getAttrId());
                pmsProductAttrValue.setAttrName(pmsAttr.getAttrName());
                pmsProductAttrValue.setAttrValue(attr.getAttrValues());
                pmsProductAttrValue.setQuickShow(attr.getShowDesc());
                pmsProductAttrValue.setSpuId(spuId);
                return pmsProductAttrValue;
            }).collect(Collectors.toList());
            PmsProductAttrValueMapper attrValueMapper = sqlSession.getMapper(PmsProductAttrValueMapper.class);
            for (PmsProductAttrValue pmsProductAttrValue : pmsProductAttrValues) {
                result += attrValueMapper.insertPmsProductAttrValue(pmsProductAttrValue);
            }
            sqlSession.flushStatements();
        }
        //5. 保存spu的积分信息: mall-sms.sms_spu_bounds
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyBeanProp(spuBoundTo, bounds);
        spuBoundTo.setSpuId(spuId);
        R<Boolean> booleanR = remoteCouponService.addSpuBounds(spuBoundTo);
        if (booleanR.getCode() != 200) {
            log.error("远程保存sku优惠信息失败");
        } else {
            result++;
        }
        //6. 保存当前spu对应的所有sku信息
        List<Skus> skus = spuSaveVo.getSkus();
        if (skus != null && skus.size() > 0) {
            for (Skus item : skus) {
                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                        break;
                    }
                }
                //6.1 sku基本信息 pms_sku_info
                PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
                BeanUtils.copyBeanProp(pmsSkuInfo, item);
                pmsSkuInfo.setBrandId(spuSaveVo.getBrandId());
                pmsSkuInfo.setCatalogId(spuSaveVo.getCatalogId());
                pmsSkuInfo.setSaleCount(0L);
                pmsSkuInfo.setSpuId(spuId);
                pmsSkuInfo.setSkuDefaultImg(defaultImg);
                result += pmsSkuInfoMapper.insertPmsSkuInfo(pmsSkuInfo);
                //6.2 sku的图片信息 pms_sku_images
                Long skuId = pmsSkuInfo.getSkuId();
                List<PmsSkuImages> pmsSkuImagesList = item.getImages().stream().map(image -> {
                    PmsSkuImages pmsSkuImages = new PmsSkuImages();
                    pmsSkuImages.setSkuId(skuId);
                    pmsSkuImages.setImgUrl(image.getImgUrl());
                    pmsSkuImages.setDefaultImg(image.getDefaultImg());
                    return pmsSkuImages;
                }).filter(pmsSkuImages -> StringUtils.isNotEmpty(pmsSkuImages.getImgUrl())).collect(Collectors.toList());
                PmsSkuImagesMapper pmsSkuImagesMapper = sqlSession.getMapper(PmsSkuImagesMapper.class);
                for (PmsSkuImages pmsSkuImages : pmsSkuImagesList) {
                    result += pmsSkuImagesMapper.insertPmsSkuImages(pmsSkuImages);
                }
                sqlSession.flushStatements();
                //6.3 sku的销售属性 pms_sku_sale_attr_value
                List<Attr> attr = item.getAttr();
                List<PmsSkuSaleAttrValue> skuSaleAttrValueList = attr.stream().map(a -> {
                    PmsSkuSaleAttrValue pmsSkuSaleAttrValue = new PmsSkuSaleAttrValue();
                    BeanUtils.copyBeanProp(pmsSkuSaleAttrValue, a);
                    pmsSkuSaleAttrValue.setSkuId(skuId);
                    return pmsSkuSaleAttrValue;
                }).collect(Collectors.toList());
                PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper = sqlSession.getMapper(PmsSkuSaleAttrValueMapper.class);
                for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                    result += pmsSkuSaleAttrValueMapper.insertPmsSkuSaleAttrValue(pmsSkuSaleAttrValue);
                }
                sqlSession.flushStatements();
                //6.4 sku的优惠、满减等信息：mall_sms.sms_sku_ladder\sms_sku_full_reduction\sms_member_price
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                skuReductionTo.setSkuId(skuId);
                BeanUtils.copyProperties(item, skuReductionTo);
                if (skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal(0)) > 0) {
                    R<Boolean> booleanR1 = remoteCouponService.addSkuSmsInfo(skuReductionTo);
                    if (booleanR1.getCode() != 200) {
                        log.error("远程保存sku优惠信息失败");
                    } else {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 修改spu信息
     *
     * @param pmsSpuInfo spu信息
     * @return 结果
     */
    @Override
    public int updatePmsSpuInfo(PmsSpuInfo pmsSpuInfo)
    {
        pmsSpuInfo.setUpdateTime(DateUtils.getNowDate());
        return pmsSpuInfoMapper.updatePmsSpuInfo(pmsSpuInfo);
    }

    /**
     * 批量删除spu信息
     *
     * @param ids 需要删除的spu信息主键
     * @return 结果
     */
    @Override
    public int deletePmsSpuInfoByIds(Long[] ids)
    {
        return pmsSpuInfoMapper.deletePmsSpuInfoByIds(ids);
    }

    /**
     * 删除spu信息信息
     *
     * @param id spu信息主键
     * @return 结果
     */
    @Override
    public int deletePmsSpuInfoById(Long id)
    {
        return pmsSpuInfoMapper.deletePmsSpuInfoById(id);
    }

    /**
     * 商品上架
     * @param spuId spuId
     * @return 结果
     */
    @Override
    public int spuUp(Long spuId) {
        //查询当前spuId对应的所有sku信息
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setSpuId(spuId);
        List<PmsSkuInfo> skus = pmsSkuInfoMapper.selectPmsSkuInfoList(pmsSkuInfo);
        List<Long> skuIdList = skus.stream().map(PmsSkuInfo::getSkuId).collect(Collectors.toList());
        //TODO 4、 查询当前sku所有可以被用来检索的规格属性
        PmsProductAttrValue pmsProductAttrValue = new PmsProductAttrValue();
        pmsProductAttrValue.setSpuId(spuId);
        List<PmsProductAttrValue> baseAttrs = pmsProductAttrValueMapper.selectPmsProductAttrValueList(pmsProductAttrValue);
        List<Long> attrIds = baseAttrs.stream().map(PmsProductAttrValue::getAttrId).collect(Collectors.toList());
        List<Long> searchAttrIds = pmsAttrMapper.selectSearchAttrIds(attrIds);
        Set<Long> idsSet = new HashSet<>(searchAttrIds);
        List<SkuEsModel.Attr> attrsList = baseAttrs.stream().filter(item -> idsSet.contains(item.getAttrId())).map(item -> {
            SkuEsModel.Attr attr = new SkuEsModel.Attr();
            BeanUtils.copyBeanProp(attr, item);
            return attr;
        }).collect(Collectors.toList());
        //TODO 1、 发送远程调用，库存系统查询是否有库存
        Map<Long, Boolean> stockMap = null;
        try {
            List<SkuHasStockVo> data = remoteWareService.getSkusHasStock(skuIdList).getData();
            if (data != null) {
                stockMap = data.stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //封装每个sku信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModel> upProducts = skus.stream().map(sku -> {
            //组装需要的数据
            SkuEsModel esModel = new SkuEsModel();
            BeanUtils.copyBeanProp(esModel, sku);
            esModel.setSkuImg(sku.getSkuDefaultImg());
            esModel.setSkuPrice(sku.getPrice());
            if (finalStockMap == null) {
                esModel.setHasStock(true);
            } else {
                esModel.setHasStock(finalStockMap.get(sku.getSkuId()));
            }
            //TODO 2、 热度评分。0
            esModel.setHotScore(0L);
            //TODO 3、 查询品牌和分类的信息
            PmsBrand pmsBrand = pmsBrandMapper.selectPmsBrandByBrandId(esModel.getBrandId());
            esModel.setBrandName(pmsBrand.getName());
            esModel.setBrandImg(pmsBrand.getLogo());
            PmsCategory pmsCategory = pmsCategoryMapper.selectPmsCategoryByCatId(esModel.getCatalogId());
            esModel.setCatalogName(pmsCategory.getName());
            //设置检索属性
            esModel.setAttrs(attrsList);
            return esModel;
        }).collect(Collectors.toList());

        //TODO 5、将数据发送给es进行保存： mall-search
        R<Boolean> r = remoteSearchService.productStatusUp(upProducts);
        if (r.getCode() == 200) {
            //TODO 6、 修改当前spu状态
            PmsSpuInfo pmsSpuInfo = new PmsSpuInfo();
            pmsSpuInfo.setId(spuId);
            pmsSpuInfo.setPublishStatus(1);
            updatePmsSpuInfo(pmsSpuInfo);
            return 1;
        } else {
            //TODO 7、 重复调用？接口幂等性；重试机制 xxx
            return 0;
        }
    }

    @Override
    public PmsSpuInfo selectPmsSpuInfoBySkuId(Long skuId) {
        PmsSkuInfo pmsSkuInfo = pmsSkuInfoMapper.selectPmsSkuInfoBySkuId(skuId);
        Long spuId = pmsSkuInfo.getSpuId();
        return selectPmsSpuInfoById(spuId);
    }
}
