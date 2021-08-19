package com.ruoyi.product.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsAttrGroup;
import com.ruoyi.product.service.IPmsAttrGroupService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 属性分组Controller
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/group")
public class PmsAttrGroupController extends BaseController
{
    @Autowired
    private IPmsAttrGroupService pmsAttrGroupService;

    /**
     * 查询属性分组列表
     */
    @PreAuthorize(hasPermi = "product:group:list")
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
    @PreAuthorize(hasPermi = "product:group:export")
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
    @PreAuthorize(hasPermi = "product:group:query")
    @GetMapping(value = "/{attrGroupId}")
    public AjaxResult getInfo(@PathVariable("attrGroupId") Long attrGroupId)
    {
        return AjaxResult.success(pmsAttrGroupService.selectPmsAttrGroupByAttrGroupId(attrGroupId));
    }

    /**
     * 新增属性分组
     */
    @PreAuthorize(hasPermi = "product:group:add")
    @Log(title = "属性分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsAttrGroup pmsAttrGroup)
    {
        return toAjax(pmsAttrGroupService.insertPmsAttrGroup(pmsAttrGroup));
    }

    /**
     * 修改属性分组
     */
    @PreAuthorize(hasPermi = "product:group:edit")
    @Log(title = "属性分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsAttrGroup pmsAttrGroup)
    {
        return toAjax(pmsAttrGroupService.updatePmsAttrGroup(pmsAttrGroup));
    }

    /**
     * 删除属性分组
     */
    @PreAuthorize(hasPermi = "product:group:remove")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrGroupIds}")
    public AjaxResult remove(@PathVariable Long[] attrGroupIds)
    {
        return toAjax(pmsAttrGroupService.deletePmsAttrGroupByAttrGroupIds(attrGroupIds));
    }
}
