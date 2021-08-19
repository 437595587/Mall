package com.ruoyi.product.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsCategory;
import com.ruoyi.product.service.IPmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 分类管理Controller
 *
 * @author xuxing
 * @date 2021-08-18
 */
@RestController
@RequestMapping("/category")
public class PmsCategoryController extends BaseController
{
    @Autowired
    private IPmsCategoryService pmsCategoryService;

    /**
     * 查询分类管理列表
     */
    @PreAuthorize(hasPermi = "product:category:list")
    @GetMapping("/list")
    public AjaxResult list(PmsCategory pmsCategory)
    {
        List<PmsCategory> list = pmsCategoryService.selectPmsCategoryList(pmsCategory);
        return AjaxResult.success(list);
    }

    /**
     * 导出分类管理列表
     */
    @PreAuthorize(hasPermi = "product:category:export")
    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsCategory pmsCategory) throws IOException
    {
        List<PmsCategory> list = pmsCategoryService.selectPmsCategoryList(pmsCategory);
        ExcelUtil<PmsCategory> util = new ExcelUtil<PmsCategory>(PmsCategory.class);
        util.exportExcel(response, list, "分类管理数据");
    }

    /**
     * 获取分类管理详细信息
     */
    @PreAuthorize(hasPermi = "product:category:query")
    @GetMapping(value = "/{catId}")
    public AjaxResult getInfo(@PathVariable("catId") Long catId)
    {
        return AjaxResult.success(pmsCategoryService.selectPmsCategoryByCatId(catId));
    }

    /**
     * 新增分类管理
     */
    @PreAuthorize(hasPermi = "product:category:add")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsCategory pmsCategory)
    {
        return toAjax(pmsCategoryService.insertPmsCategory(pmsCategory));
    }

    /**
     * 修改分类管理
     */
    @PreAuthorize(hasPermi = "product:category:edit")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsCategory pmsCategory)
    {
        return toAjax(pmsCategoryService.updatePmsCategory(pmsCategory));
    }

    /**
     * 删除分类管理
     */
    @PreAuthorize(hasPermi = "product:category:remove")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{catIds}")
    public AjaxResult remove(@PathVariable Long[] catIds)
    {
        return toAjax(pmsCategoryService.deletePmsCategoryByCatIds(catIds));
    }
}
