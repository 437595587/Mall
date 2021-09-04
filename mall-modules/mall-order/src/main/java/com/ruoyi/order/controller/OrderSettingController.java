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
import com.ruoyi.order.domain.OrderSetting;
import com.ruoyi.order.service.IOrderSettingService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 订单配置信息Controller
 * 
 * @author xuxing
 * @date 2021-08-31
 */
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController extends BaseController
{
    @Autowired
    private IOrderSettingService orderSettingService;

    /**
     * 查询订单配置信息列表
     */
    @PreAuthorize(hasPermi = "order:orderSetting:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderSetting orderSetting)
    {
        startPage();
        List<OrderSetting> list = orderSettingService.selectOrderSettingList(orderSetting);
        return getDataTable(list);
    }

    /**
     * 导出订单配置信息列表
     */
    @PreAuthorize(hasPermi = "order:orderSetting:export")
    @Log(title = "订单配置信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderSetting orderSetting) throws IOException
    {
        List<OrderSetting> list = orderSettingService.selectOrderSettingList(orderSetting);
        ExcelUtil<OrderSetting> util = new ExcelUtil<OrderSetting>(OrderSetting.class);
        util.exportExcel(response, list, "订单配置信息数据");
    }

    /**
     * 获取订单配置信息详细信息
     */
    @PreAuthorize(hasPermi = "order:orderSetting:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderSettingService.selectOrderSettingById(id));
    }

    /**
     * 新增订单配置信息
     */
    @PreAuthorize(hasPermi = "order:orderSetting:add")
    @Log(title = "订单配置信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderSetting orderSetting)
    {
        return toAjax(orderSettingService.insertOrderSetting(orderSetting));
    }

    /**
     * 修改订单配置信息
     */
    @PreAuthorize(hasPermi = "order:orderSetting:edit")
    @Log(title = "订单配置信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderSetting orderSetting)
    {
        return toAjax(orderSettingService.updateOrderSetting(orderSetting));
    }

    /**
     * 删除订单配置信息
     */
    @PreAuthorize(hasPermi = "order:orderSetting:remove")
    @Log(title = "订单配置信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderSettingService.deleteOrderSettingByIds(ids));
    }
}
