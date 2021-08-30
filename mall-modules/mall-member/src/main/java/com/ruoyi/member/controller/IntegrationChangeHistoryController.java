package com.ruoyi.member.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.member.domain.IntegrationChangeHistory;
import com.ruoyi.member.service.IIntegrationChangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 积分变化历史记录Controller
 *
 * @author xuxing
 * @date 2021-08-22
 */
@RestController
@RequestMapping("/integrationChangeHistory")
public class IntegrationChangeHistoryController extends BaseController
{
    @Autowired
    private IIntegrationChangeHistoryService integrationChangeHistoryService;

    /**
     * 查询积分变化历史记录列表
     */
    @PreAuthorize(hasPermi = "member:integrationChangeHistory:list")
    @GetMapping("/list")
    public TableDataInfo list(IntegrationChangeHistory integrationChangeHistory)
    {
        startPage();
        List<IntegrationChangeHistory> list = integrationChangeHistoryService.selectIntegrationChangeHistoryList(integrationChangeHistory);
        return getDataTable(list);
    }

    /**
     * 导出积分变化历史记录列表
     */
    @PreAuthorize(hasPermi = "member:integrationChangeHistory:export")
    @Log(title = "积分变化历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntegrationChangeHistory integrationChangeHistory) throws IOException
    {
        List<IntegrationChangeHistory> list = integrationChangeHistoryService.selectIntegrationChangeHistoryList(integrationChangeHistory);
        ExcelUtil<IntegrationChangeHistory> util = new ExcelUtil<IntegrationChangeHistory>(IntegrationChangeHistory.class);
        util.exportExcel(response, list, "积分变化历史记录数据");
    }

    /**
     * 获取积分变化历史记录详细信息
     */
    @PreAuthorize(hasPermi = "member:integrationChangeHistory:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(integrationChangeHistoryService.selectIntegrationChangeHistoryById(id));
    }

    /**
     * 新增积分变化历史记录
     */
    @PreAuthorize(hasPermi = "member:integrationChangeHistory:add")
    @Log(title = "积分变化历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntegrationChangeHistory integrationChangeHistory)
    {
        return toAjax(integrationChangeHistoryService.insertIntegrationChangeHistory(integrationChangeHistory));
    }

    /**
     * 修改积分变化历史记录
     */
    @PreAuthorize(hasPermi = "member:integrationChangeHistory:edit")
    @Log(title = "积分变化历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntegrationChangeHistory integrationChangeHistory)
    {
        return toAjax(integrationChangeHistoryService.updateIntegrationChangeHistory(integrationChangeHistory));
    }

    /**
     * 删除积分变化历史记录
     */
    @PreAuthorize(hasPermi = "member:integrationChangeHistory:remove")
    @Log(title = "积分变化历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(integrationChangeHistoryService.deleteIntegrationChangeHistoryByIds(ids));
    }
}
