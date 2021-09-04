package com.ruoyi.product.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsSkuSaleAttrValue;
import com.ruoyi.product.service.IPmsSkuSaleAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * sku销售属性&值Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/saleAttrValue")
public class PmsSkuSaleAttrValueController extends BaseController {
    @Autowired
    private IPmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;

    @GetMapping("/skuSaleAttrValues/{skuId}")
    public R<List<String>> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId) {
        List<String> list = pmsSkuSaleAttrValueService.selectSkuSaleAttrValues(skuId);
        return R.ok(list);
    }

    /**
     * 查询sku销售属性&值列表
     */
    @PreAuthorize(hasPermi = "product:saleAttrValue:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSkuSaleAttrValue pmsSkuSaleAttrValue) {
        startPage();
        List<PmsSkuSaleAttrValue> list = pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueList(pmsSkuSaleAttrValue);
        return getDataTable(list);
    }

    /**
     * 导出sku销售属性&值列表
     */
    @PreAuthorize(hasPermi = "product:saleAttrValue:export")
    @Log(title = "sku销售属性&值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSkuSaleAttrValue pmsSkuSaleAttrValue) throws IOException {
        List<PmsSkuSaleAttrValue> list = pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueList(pmsSkuSaleAttrValue);
        ExcelUtil<PmsSkuSaleAttrValue> util = new ExcelUtil<PmsSkuSaleAttrValue>(PmsSkuSaleAttrValue.class);
        util.exportExcel(response, list, "sku销售属性&值数据");
    }

    /**
     * 获取sku销售属性&值详细信息
     */
    @PreAuthorize(hasPermi = "product:saleAttrValue:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueById(id));
    }

    /**
     * 新增sku销售属性&值
     */
    @PreAuthorize(hasPermi = "product:saleAttrValue:add")
    @Log(title = "sku销售属性&值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSkuSaleAttrValue pmsSkuSaleAttrValue) {
        return toAjax(pmsSkuSaleAttrValueService.insertPmsSkuSaleAttrValue(pmsSkuSaleAttrValue));
    }

    /**
     * 修改sku销售属性&值
     */
    @PreAuthorize(hasPermi = "product:saleAttrValue:edit")
    @Log(title = "sku销售属性&值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSkuSaleAttrValue pmsSkuSaleAttrValue) {
        return toAjax(pmsSkuSaleAttrValueService.updatePmsSkuSaleAttrValue(pmsSkuSaleAttrValue));
    }

    /**
     * 删除sku销售属性&值
     */
    @PreAuthorize(hasPermi = "product:saleAttrValue:remove")
    @Log(title = "sku销售属性&值", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(pmsSkuSaleAttrValueService.deletePmsSkuSaleAttrValueByIds(ids));
    }
}
