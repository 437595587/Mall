package com.ruoyi.order.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 订单Controller
 *
 * @author xuxing
 * @date 2021-08-31
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController
{
    @Autowired
    private IOrderService orderService;

    @GetMapping("/listWithItem")
    public R<List<Order>> listOrderWithItem() {
        startPage();
        List<Order> list = orderService.selectOrderWithItem();
        return R.ok(list);
    }

    @GetMapping("/{orderSn}/status")
    public R<Order> getOrderStatus(@PathVariable("orderSn") String orderSn) {
        Order order = orderService.selectOrderByOrderSn(orderSn);
        return R.ok(order);
    }

    /**
     * 查询订单列表
     */
    @PreAuthorize(hasPermi = "order:order:list")
    @GetMapping("/list")
    public TableDataInfo list(Order order)
    {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize(hasPermi = "order:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Order order) throws IOException
    {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize(hasPermi = "order:order:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderService.selectOrderById(id));
    }

    /**
     * 新增订单
     */
    @PreAuthorize(hasPermi = "order:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Order order)
    {
        return toAjax(orderService.insertOrder(order));
    }

    /**
     * 修改订单
     */
    @PreAuthorize(hasPermi = "order:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Order order)
    {
        return toAjax(orderService.updateOrder(order));
    }

    /**
     * 删除订单
     */
    @PreAuthorize(hasPermi = "order:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderService.deleteOrderByIds(ids));
    }
}
