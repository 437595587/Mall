package com.ruoyi.product.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsAttrAttrgroupRelation;
import com.ruoyi.product.service.IPmsAttrAttrgroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 属性&属性分组关联Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/attrgroupRelation")
public class PmsAttrAttrgroupRelationController extends BaseController
{
    @Autowired
    private IPmsAttrAttrgroupRelationService pmsAttrAttrgroupRelationService;

    /**
     * 查询属性&属性分组关联列表
     */
    @PreAuthorize(hasPermi = "product:relation:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation)
    {
        startPage();
        List<PmsAttrAttrgroupRelation> list = pmsAttrAttrgroupRelationService.selectPmsAttrAttrgroupRelationList(pmsAttrAttrgroupRelation);
        return getDataTable(list);
    }

    /**
     * 导出属性&属性分组关联列表
     */
    @PreAuthorize(hasPermi = "product:relation:export")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation) throws IOException
    {
        List<PmsAttrAttrgroupRelation> list = pmsAttrAttrgroupRelationService.selectPmsAttrAttrgroupRelationList(pmsAttrAttrgroupRelation);
        ExcelUtil<PmsAttrAttrgroupRelation> util = new ExcelUtil<PmsAttrAttrgroupRelation>(PmsAttrAttrgroupRelation.class);
        util.exportExcel(response, list, "属性&属性分组关联数据");
    }

    /**
     * 获取属性&属性分组关联详细信息
     */
    @PreAuthorize(hasPermi = "product:relation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pmsAttrAttrgroupRelationService.selectPmsAttrAttrgroupRelationById(id));
    }

    /**
     * 新增属性&属性分组关联
     */
    @PreAuthorize(hasPermi = "product:relation:add")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation)
    {
        return toAjax(pmsAttrAttrgroupRelationService.insertPmsAttrAttrgroupRelation(pmsAttrAttrgroupRelation));
    }

    /**
     * 修改属性&属性分组关联
     */
    @PreAuthorize(hasPermi = "product:relation:edit")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation)
    {
        return toAjax(pmsAttrAttrgroupRelationService.updatePmsAttrAttrgroupRelation(pmsAttrAttrgroupRelation));
    }

    /**
     * 删除属性&属性分组关联
     */
    @PreAuthorize(hasPermi = "product:relation:remove")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsAttrAttrgroupRelationService.deletePmsAttrAttrgroupRelationByIds(ids));
    }
}
