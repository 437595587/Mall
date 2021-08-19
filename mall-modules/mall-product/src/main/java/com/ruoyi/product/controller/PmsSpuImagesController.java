package com.ruoyi.product.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsSpuImages;
import com.ruoyi.product.service.IPmsSpuImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * spu图片Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/spuImages")
public class PmsSpuImagesController extends BaseController
{
    @Autowired
    private IPmsSpuImagesService pmsSpuImagesService;

    /**
     * 查询spu图片列表
     */
    @PreAuthorize(hasPermi = "product:images:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSpuImages pmsSpuImages)
    {
        startPage();
        List<PmsSpuImages> list = pmsSpuImagesService.selectPmsSpuImagesList(pmsSpuImages);
        return getDataTable(list);
    }

    /**
     * 导出spu图片列表
     */
    @PreAuthorize(hasPermi = "product:images:export")
    @Log(title = "spu图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSpuImages pmsSpuImages) throws IOException
    {
        List<PmsSpuImages> list = pmsSpuImagesService.selectPmsSpuImagesList(pmsSpuImages);
        ExcelUtil<PmsSpuImages> util = new ExcelUtil<PmsSpuImages>(PmsSpuImages.class);
        util.exportExcel(response, list, "spu图片数据");
    }

    /**
     * 获取spu图片详细信息
     */
    @PreAuthorize(hasPermi = "product:images:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pmsSpuImagesService.selectPmsSpuImagesById(id));
    }

    /**
     * 新增spu图片
     */
    @PreAuthorize(hasPermi = "product:images:add")
    @Log(title = "spu图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSpuImages pmsSpuImages)
    {
        return toAjax(pmsSpuImagesService.insertPmsSpuImages(pmsSpuImages));
    }

    /**
     * 修改spu图片
     */
    @PreAuthorize(hasPermi = "product:images:edit")
    @Log(title = "spu图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSpuImages pmsSpuImages)
    {
        return toAjax(pmsSpuImagesService.updatePmsSpuImages(pmsSpuImages));
    }

    /**
     * 删除spu图片
     */
    @PreAuthorize(hasPermi = "product:images:remove")
    @Log(title = "spu图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSpuImagesService.deletePmsSpuImagesByIds(ids));
    }
}
