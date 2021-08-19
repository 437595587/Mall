package com.ruoyi.product.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsCategoryBrandRelation;
import com.ruoyi.product.service.IPmsCategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 品牌分类关联Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/categoryBrandRelation")
public class PmsCategoryBrandRelationController extends BaseController
{
    @Autowired
    private IPmsCategoryBrandRelationService pmsCategoryBrandRelationService;

    /**
     * 查询品牌分类关联列表
     */
    @PreAuthorize(hasPermi = "product:relation:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        startPage();
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
        return getDataTable(list);
    }

    /**
     * 导出品牌分类关联列表
     */
    @PreAuthorize(hasPermi = "product:relation:export")
    @Log(title = "品牌分类关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsCategoryBrandRelation pmsCategoryBrandRelation) throws IOException
    {
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
        ExcelUtil<PmsCategoryBrandRelation> util = new ExcelUtil<PmsCategoryBrandRelation>(PmsCategoryBrandRelation.class);
        util.exportExcel(response, list, "品牌分类关联数据");
    }

    /**
     * 获取品牌分类关联详细信息
     */
    @PreAuthorize(hasPermi = "product:relation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationById(id));
    }

    /**
     * 新增品牌分类关联
     */
    @PreAuthorize(hasPermi = "product:relation:add")
    @Log(title = "品牌分类关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return toAjax(pmsCategoryBrandRelationService.insertPmsCategoryBrandRelation(pmsCategoryBrandRelation));
    }

    /**
     * 修改品牌分类关联
     */
    @PreAuthorize(hasPermi = "product:relation:edit")
    @Log(title = "品牌分类关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return toAjax(pmsCategoryBrandRelationService.updatePmsCategoryBrandRelation(pmsCategoryBrandRelation));
    }

    /**
     * 删除品牌分类关联
     */
    @PreAuthorize(hasPermi = "product:relation:remove")
    @Log(title = "品牌分类关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsCategoryBrandRelationService.deletePmsCategoryBrandRelationByIds(ids));
    }
}
