package com.ruoyi.product.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsAttr;
import com.ruoyi.product.domain.PmsAttrGroup;
import com.ruoyi.product.service.IPmsAttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 属性分组Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/attrGroup")
public class PmsAttrGroupController extends BaseController
{
    @Autowired
    private IPmsAttrGroupService pmsAttrGroupService;

    /**
     * 查询属性分组列表
     */
    @PreAuthorize(hasPermi = "product:attrGroup:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsAttrGroup pmsAttrGroup)
    {
        startPage();
        List<PmsAttrGroup> list = pmsAttrGroupService.selectPmsAttrGroupList(pmsAttrGroup);
        return getDataTable(list);
    }

    /**
     * 导出属性分组列表
     */
    @PreAuthorize(hasPermi = "product:attrGroup:export")
    @Log(title = "属性分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsAttrGroup pmsAttrGroup) throws IOException
    {
        List<PmsAttrGroup> list = pmsAttrGroupService.selectPmsAttrGroupList(pmsAttrGroup);
        ExcelUtil<PmsAttrGroup> util = new ExcelUtil<PmsAttrGroup>(PmsAttrGroup.class);
        util.exportExcel(response, list, "属性分组数据");
    }

    /**
     * 获取属性分组详细信息
     */
    @PreAuthorize(hasPermi = "product:attrGroup:query")
    @GetMapping(value = "/{attrGroupId}")
    public AjaxResult getInfo(@PathVariable("attrGroupId") Long attrGroupId)
    {
        return AjaxResult.success(pmsAttrGroupService.selectPmsAttrGroupByAttrGroupId(attrGroupId));
    }

    /**
     * 新增属性分组
     */
    @PreAuthorize(hasPermi = "product:attrGroup:add")
    @Log(title = "属性分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsAttrGroup pmsAttrGroup)
    {
        return toAjax(pmsAttrGroupService.insertPmsAttrGroup(pmsAttrGroup));
    }

    /**
     * 修改属性分组
     */
    @PreAuthorize(hasPermi = "product:attrGroup:edit")
    @Log(title = "属性分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsAttrGroup pmsAttrGroup)
    {
        return toAjax(pmsAttrGroupService.updatePmsAttrGroup(pmsAttrGroup));
    }

    /**
     * 删除属性分组
     */
    @PreAuthorize(hasPermi = "product:attrGroup:remove")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrGroupIds}")
    public AjaxResult remove(@PathVariable Long[] attrGroupIds)
    {
        return toAjax(pmsAttrGroupService.deletePmsAttrGroupByAttrGroupIds(attrGroupIds));
    }

    /**
     * 属性分组关联信息
     */
    @PreAuthorize(hasPermi = "product:attr:list")
    @GetMapping("/attr/{attrGroupId}")
    public TableDataInfo getAttrInfo(@PathVariable Long attrGroupId, PmsAttr pmsAttr) {
        startPage();
        List<PmsAttr> list = pmsAttrGroupService.selectPmsAttrListByGroupId(attrGroupId, pmsAttr);
        return getDataTable(list);
    }

    /**
     * 属性分组关联信息
     */
    @PreAuthorize(hasPermi = "product:attr:delete")
    @Log(title = "属性分组关联属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/attr/{attrIds}")
    public AjaxResult removeAttr(@PathVariable Long[] attrIds) {
        return toAjax(pmsAttrGroupService.deletePmsAttrAttrGroupByGroupId(attrIds));
    }

    /**
     * 查询未分组的属性
     */
    @PreAuthorize(hasPermi = "product:attr:list")
    @GetMapping("/noRelationAttr")
    public TableDataInfo getNoRelationAttrInfo(PmsAttr pmsAttr) {
        startPage();
        return getDataTable(pmsAttrGroupService.selectPmsAttrNoRelation(pmsAttr));
    }

    @PreAuthorize(hasPermi = "product:attr:add")
    @Log(title = "属性分组关联属性", businessType = BusinessType.INSERT)
    @PostMapping("/noRelationAttr/{attrGroupId}/{attrIds}")
    public AjaxResult addNoRelationAttr(@PathVariable Long attrGroupId, @PathVariable Long[] attrIds) {
        return toAjax(pmsAttrGroupService.addPmsAttrAttrRelation(attrGroupId, attrIds));
    }

    /**
     * 根据分类id查询商品分组属性列表
     */
    @PreAuthorize(hasPermi = "product:attr:list")
    @GetMapping("/listAttrGroupAttrs/{catalogId}")
    public AjaxResult listAttrGroupAttrs(@PathVariable Long catalogId)
    {
        return AjaxResult.success(pmsAttrGroupService.selectAttrGroupAttrsList(catalogId));
    }
}
