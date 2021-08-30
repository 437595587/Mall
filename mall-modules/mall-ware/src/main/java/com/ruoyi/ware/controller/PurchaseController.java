package com.ruoyi.ware.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.ware.domain.Purchase;
import com.ruoyi.ware.domain.vo.MergeVo;
import com.ruoyi.ware.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 采购单Controller
 *
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController extends BaseController
{
    @Autowired
    private IPurchaseService purchaseService;

    /**
     * 合并采购需求
     */
    @PreAuthorize(hasPermi = "ware:purchase:add")
    @Log(title = "合并采购单", businessType = BusinessType.INSERT)
    @PostMapping("/merge")
    public AjaxResult merge(@RequestBody MergeVo mergeVo) {
        return toAjax(purchaseService.mergePurchase(mergeVo));
    }

    /**
     * 查询采购单列表
     */
    @PreAuthorize(hasPermi = "ware:purchase:list")
    @GetMapping("/listUnReceivePurchase")
    public AjaxResult listUnReceivePurchase()
    {
        List<Purchase> list = purchaseService.selectUnReceivePurchaseList();
        return AjaxResult.success(list);
    }

    /**
     * 查询采购单列表
     */
    @PreAuthorize(hasPermi = "ware:purchase:list")
    @GetMapping("/list")
    public TableDataInfo list(Purchase purchase)
    {
        startPage();
        List<Purchase> list = purchaseService.selectPurchaseList(purchase);
        return getDataTable(list);
    }

    /**
     * 导出采购单列表
     */
    @PreAuthorize(hasPermi = "ware:purchase:export")
    @Log(title = "采购单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Purchase purchase) throws IOException
    {
        List<Purchase> list = purchaseService.selectPurchaseList(purchase);
        ExcelUtil<Purchase> util = new ExcelUtil<Purchase>(Purchase.class);
        util.exportExcel(response, list, "采购单数据");
    }

    /**
     * 获取采购单详细信息
     */
    @PreAuthorize(hasPermi = "ware:purchase:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(purchaseService.selectPurchaseById(id));
    }

    /**
     * 新增采购单
     */
    @PreAuthorize(hasPermi = "ware:purchase:add")
    @Log(title = "采购单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Purchase purchase)
    {
        return toAjax(purchaseService.insertPurchase(purchase));
    }

    /**
     * 修改采购单
     */
    @PreAuthorize(hasPermi = "ware:purchase:edit")
    @Log(title = "采购单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Purchase purchase)
    {
        return toAjax(purchaseService.updatePurchase(purchase));
    }

    /**
     * 删除采购单
     */
    @PreAuthorize(hasPermi = "ware:purchase:remove")
    @Log(title = "采购单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(purchaseService.deletePurchaseByIds(ids));
    }
}
