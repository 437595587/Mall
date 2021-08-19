package com.ruoyi.product.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsSkuImages;
import com.ruoyi.product.service.IPmsSkuImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * sku图片Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/skuImages")
public class PmsSkuImagesController extends BaseController
{
    @Autowired
    private IPmsSkuImagesService pmsSkuImagesService;

    /**
     * 查询sku图片列表
     */
    @PreAuthorize(hasPermi = "product:images:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSkuImages pmsSkuImages)
    {
        startPage();
        List<PmsSkuImages> list = pmsSkuImagesService.selectPmsSkuImagesList(pmsSkuImages);
        return getDataTable(list);
    }

    /**
     * 导出sku图片列表
     */
    @PreAuthorize(hasPermi = "product:images:export")
    @Log(title = "sku图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSkuImages pmsSkuImages) throws IOException
    {
        List<PmsSkuImages> list = pmsSkuImagesService.selectPmsSkuImagesList(pmsSkuImages);
        ExcelUtil<PmsSkuImages> util = new ExcelUtil<PmsSkuImages>(PmsSkuImages.class);
        util.exportExcel(response, list, "sku图片数据");
    }

    /**
     * 获取sku图片详细信息
     */
    @PreAuthorize(hasPermi = "product:images:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pmsSkuImagesService.selectPmsSkuImagesById(id));
    }

    /**
     * 新增sku图片
     */
    @PreAuthorize(hasPermi = "product:images:add")
    @Log(title = "sku图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSkuImages pmsSkuImages)
    {
        return toAjax(pmsSkuImagesService.insertPmsSkuImages(pmsSkuImages));
    }

    /**
     * 修改sku图片
     */
    @PreAuthorize(hasPermi = "product:images:edit")
    @Log(title = "sku图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSkuImages pmsSkuImages)
    {
        return toAjax(pmsSkuImagesService.updatePmsSkuImages(pmsSkuImages));
    }

    /**
     * 删除sku图片
     */
    @PreAuthorize(hasPermi = "product:images:remove")
    @Log(title = "sku图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSkuImagesService.deletePmsSkuImagesByIds(ids));
    }
}
