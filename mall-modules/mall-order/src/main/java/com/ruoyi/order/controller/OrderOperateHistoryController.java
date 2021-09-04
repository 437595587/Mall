package com.ruoyi.order.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.order.domain.OrderOperateHistory;
import com.ruoyi.order.service.IOrderOperateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 订单操作历史记录Controller
 *
 * @author xuxing
 * @date 2021-08-31
 */
@RestController
@RequestMapping("/orderOperateHistory")
public class OrderOperateHistoryController extends BaseController
{
    @Autowired
    private IOrderOperateHistoryService orderOperateHistoryService;

    /**
     * 查询订单操作历史记录列表
     */
    @PreAuthorize(hasPermi = "order:orderOperateHistory:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderOperateHistory orderOperateHistory)
    {
        startPage();
        List<OrderOperateHistory> list = orderOperateHistoryService.selectOrderOperateHistoryList(orderOperateHistory);
        return getDataTable(list);
    }

    /**
     * 导出订单操作历史记录列表
     */
    @PreAuthorize(hasPermi = "order:orderOperateHistory:export")
    @Log(title = "订单操作历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderOperateHistory orderOperateHistory) throws IOException
    {
        List<OrderOperateHistory> list = orderOperateHistoryService.selectOrderOperateHistoryList(orderOperateHistory);
        ExcelUtil<OrderOperateHistory> util = new ExcelUtil<OrderOperateHistory>(OrderOperateHistory.class);
        util.exportExcel(response, list, "订单操作历史记录数据");
    }

    /**
     * 获取订单操作历史记录详细信息
     */
    @PreAuthorize(hasPermi = "order:orderOperateHistory:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderOperateHistoryService.selectOrderOperateHistoryById(id));
    }

    /**
     * 新增订单操作历史记录
     */
    @PreAuthorize(hasPermi = "order:orderOperateHistory:add")
    @Log(title = "订单操作历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderOperateHistory orderOperateHistory)
    {
        return toAjax(orderOperateHistoryService.insertOrderOperateHistory(orderOperateHistory));
    }

    /**
     * 修改订单操作历史记录
     */
    @PreAuthorize(hasPermi = "order:orderOperateHistory:edit")
    @Log(title = "订单操作历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderOperateHistory orderOperateHistory)
    {
        return toAjax(orderOperateHistoryService.updateOrderOperateHistory(orderOperateHistory));
    }

    /**
     * 删除订单操作历史记录
     */
    @PreAuthorize(hasPermi = "order:orderOperateHistory:remove")
    @Log(title = "订单操作历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderOperateHistoryService.deleteOrderOperateHistoryByIds(ids));
    }
}
