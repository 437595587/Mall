package com.ruoyi.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.mall.api.to.es.SkuEsModel;
import com.ruoyi.search.config.MallElasticSearchConfig;
import com.ruoyi.search.constant.EsConstant;
import com.ruoyi.search.service.IProductSaveService;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSaveServiceImpl implements IProductSaveService {

    private static final Logger log = LoggerFactory.getLogger(ProductSaveServiceImpl.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public int productStatusUp(List<SkuEsModel> skuEsModels) {
        //保存到es
        //1. 给es建立索引。product, 建立好映射关系
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModels) {
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            String s = JSON.toJSONString(model);
            indexRequest.source(s, XContentType.JSON);

            bulkRequest.add(indexRequest);
        }
        try {
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, MallElasticSearchConfig.COMMON_OPTIONS);
            boolean b = bulk.hasFailures();
            List<String> collect = Arrays.stream(bulk.getItems()).map(BulkItemResponse::getId).collect(Collectors.toList());
            if (b) {
                log.error("商品上架错误：{}", collect);
            }
            return b ? 0 : 1;
        } catch (IOException e) {
            log.error("es保存失败{}", e.getMessage());
            return 0;
        }
    }
}
