package com.ruoyi.search.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.mall.api.to.es.SkuEsModel;
import com.ruoyi.search.service.IProductSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/save")
@RestController
public class ElasticSearchSaveController extends BaseController {

    @Autowired
    private IProductSaveService productSaveService;

    @PostMapping("/product")
    public R<Boolean> productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
        int result = productSaveService.productStatusUp(skuEsModels);
        if (result > 0) {
            return R.ok(true);
        } else {
            return R.fail(false);
        }
    }
}
