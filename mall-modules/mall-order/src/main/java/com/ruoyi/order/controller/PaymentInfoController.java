package com.ruoyi.order.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.order.domain.PaymentInfo;
import com.ruoyi.order.service.IPaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 支付信息Controller
 *
 * @author xuxing
 * @date 2021-08-31
 */
@RestController
@RequestMapping("/paymentInfo")
public class PaymentInfoController extends BaseController
{
    @Autowired
    private IPaymentInfoService paymentInfoService;

    /**
     * 查询支付信息列表
     */
    @PreAuthorize(hasPermi = "order:paymentInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(PaymentInfo paymentInfo)
    {
        startPage();
        List<PaymentInfo> list = paymentInfoService.selectPaymentInfoList(paymentInfo);
        return getDataTable(list);
    }

    /**
     * 导出支付信息列表
     */
    @PreAuthorize(hasPermi = "order:paymentInfo:export")
    @Log(title = "支付信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaymentInfo paymentInfo) throws IOException
    {
        List<PaymentInfo> list = paymentInfoService.selectPaymentInfoList(paymentInfo);
        ExcelUtil<PaymentInfo> util = new ExcelUtil<PaymentInfo>(PaymentInfo.class);
        util.exportExcel(response, list, "支付信息数据");
    }

    /**
     * 获取支付信息详细信息
     */
    @PreAuthorize(hasPermi = "order:paymentInfo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(paymentInfoService.selectPaymentInfoById(id));
    }

    /**
     * 新增支付信息
     */
    @PreAuthorize(hasPermi = "order:paymentInfo:add")
    @Log(title = "支付信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaymentInfo paymentInfo)
    {
        return toAjax(paymentInfoService.insertPaymentInfo(paymentInfo));
    }

    /**
     * 修改支付信息
     */
    @PreAuthorize(hasPermi = "order:paymentInfo:edit")
    @Log(title = "支付信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaymentInfo paymentInfo)
    {
        return toAjax(paymentInfoService.updatePaymentInfo(paymentInfo));
    }

    /**
     * 删除支付信息
     */
    @PreAuthorize(hasPermi = "order:paymentInfo:remove")
    @Log(title = "支付信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(paymentInfoService.deletePaymentInfoByIds(ids));
    }
}
