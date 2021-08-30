package com.ruoyi.search.service;

import com.ruoyi.mall.api.to.es.SkuEsModel;

import java.util.List;

public interface IProductSaveService {

    int productStatusUp(List<SkuEsModel> skuEsModels);
}
