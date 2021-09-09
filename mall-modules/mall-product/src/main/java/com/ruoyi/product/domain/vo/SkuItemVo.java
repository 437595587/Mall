package com.ruoyi.product.domain.vo;

import com.ruoyi.mall.api.vo.SeckillInfoVo;
import com.ruoyi.product.domain.PmsSkuImages;
import com.ruoyi.product.domain.PmsSkuInfo;
import com.ruoyi.product.domain.PmsSpuInfoDesc;
import lombok.Data;

import java.util.List;

@Data
public class SkuItemVo {
    //1、sku基本信息
    private PmsSkuInfo info;
    //2、sku图片信息
    private List<PmsSkuImages> images;
    //3、spu销售属性组合
    private List<ItemSaleAttrVo> saleAttr;
    //4、spu的介绍
    private PmsSpuInfoDesc desc;
    //5、spu的规格参数信息
    private List<SpuItemAttrGroupVo> groupAttrs;

    //6、秒杀优惠信息
    private SeckillInfoVo seckillInfoVo;

    private boolean hasStock = true;

    @Data
    public static class ItemSaleAttrVo{
        private Long attrId;
        private String attrName;

        /** AttrValueWithSkuIdVo两个属性 attrValue、skuIds */
        private List<AttrValueWithSkuIdVo> attrValues;

        @Data
        public static class AttrValueWithSkuIdVo {
            public String skuIds;
            public String attrValue;
        }
    }

    @Data
    public static class SpuItemAttrGroupVo {
        private String groupName;
        private List<SpuBaseAttrVo> attrs;
    }

    @Data
    public static class SpuBaseAttrVo {
        private String attrName;
        private String attrValue;
    }
}
