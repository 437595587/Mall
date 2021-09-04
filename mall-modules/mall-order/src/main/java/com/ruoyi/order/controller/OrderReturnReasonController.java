package com.ruoyi.order.controller;

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
import com.ruoyi.order.domain.OrderReturnReason;
import com.ruoyi.order.service.IOrderReturnReasonService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 退货原因Controller
 * 
 * @author xuxing
 * @date 2021-08-31
 */
@RestController
@RequestMapping("/orderReturnReason")
public class OrderReturnReasonController extends BaseController
{
    @Autowired
    private IOrderReturnReasonService orderReturnReasonService;

    /**
     * 查询退货原因列表
     */
    @PreAuthorize(hasPermi = "order:orderReturnReason:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderReturnReason orderReturnReason)
    {
        startPage();
        List<OrderReturnReason> list = orderReturnReasonService.selectOrderReturnReasonList(orderReturnReason);
        return getDataTable(list);
    }

    /**
     * 导出退货原因列表
     */
    @PreAuthorize(hasPermi = "order:orderReturnReason:export")
    @Log(title = "退货原因", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderReturnReason orderReturnReason) throws IOException
    {
        List<OrderReturnReason> list = orderReturnReasonService.selectOrderReturnReasonList(orderReturnReason);
        ExcelUtil<OrderReturnReason> util = new ExcelUtil<OrderReturnReason>(OrderReturnReason.class);
        util.exportExcel(response, list, "退货原因数据");
    }

    /**
     * 获取退货原因详细信息
     */
    @PreAuthorize(hasPermi = "order:orderReturnReason:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderReturnReasonService.selectOrderReturnReasonById(id));
    }

    /**
     * 新增退货原因
     */
    @PreAuthorize(hasPermi = "order:orderReturnReason:add")
    @Log(title = "退货原因", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderReturnReason orderReturnReason)
    {
        return toAjax(orderReturnReasonService.insertOrderReturnReason(orderReturnReason));
    }

    /**
     * 修改退货原因
     */
    @PreAuthorize(hasPermi = "order:orderReturnReason:edit")
    @Log(title = "退货原因", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderReturnReason orderReturnReason)
    {
        return toAjax(orderReturnReasonService.updateOrderReturnReason(orderReturnReason));
    }

    /**
     * 删除退货原因
     */
    @PreAuthorize(hasPermi = "order:orderReturnReason:remove")
    @Log(title = "退货原因", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderReturnReasonService.deleteOrderReturnReasonByIds(ids));
    }
}
