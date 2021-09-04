package com.ruoyi.order.controller;

import com.rabbitmq.client.Channel;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.order.domain.OrderItem;
import com.ruoyi.order.domain.OrderReturnReason;
import com.ruoyi.order.service.IOrderItemService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 订单项信息Controller
 *
 * @author xuxing
 * @date 2021-08-31
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController extends BaseController
{
    @Autowired
    private IOrderItemService orderItemService;

    /**
     * 查询订单项信息列表
     */
    @PreAuthorize(hasPermi = "order:orderItem:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderItem orderItem)
    {
        startPage();
        List<OrderItem> list = orderItemService.selectOrderItemList(orderItem);
        return getDataTable(list);
    }

    /**
     * 导出订单项信息列表
     */
    @PreAuthorize(hasPermi = "order:orderItem:export")
    @Log(title = "订单项信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderItem orderItem) throws IOException
    {
        List<OrderItem> list = orderItemService.selectOrderItemList(orderItem);
        ExcelUtil<OrderItem> util = new ExcelUtil<OrderItem>(OrderItem.class);
        util.exportExcel(response, list, "订单项信息数据");
    }

    /**
     * 获取订单项信息详细信息
     */
    @PreAuthorize(hasPermi = "order:orderItem:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderItemService.selectOrderItemById(id));
    }

    /**
     * 新增订单项信息
     */
    @PreAuthorize(hasPermi = "order:orderItem:add")
    @Log(title = "订单项信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderItem orderItem)
    {
        return toAjax(orderItemService.insertOrderItem(orderItem));
    }

    /**
     * 修改订单项信息
     */
    @PreAuthorize(hasPermi = "order:orderItem:edit")
    @Log(title = "订单项信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderItem orderItem)
    {
        return toAjax(orderItemService.updateOrderItem(orderItem));
    }

    /**
     * 删除订单项信息
     */
    @PreAuthorize(hasPermi = "order:orderItem:remove")
    @Log(title = "订单项信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderItemService.deleteOrderItemByIds(ids));
    }
}
