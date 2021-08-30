package com.ruoyi.coupon.controller;

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
import com.ruoyi.coupon.domain.CouponHistory;
import com.ruoyi.coupon.service.ICouponHistoryService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 优惠券领取历史记录Controller
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/couponHistory")
public class CouponHistoryController extends BaseController
{
    @Autowired
    private ICouponHistoryService couponHistoryService;

    /**
     * 查询优惠券领取历史记录列表
     */
    @PreAuthorize(hasPermi = "coupon:couponHistory:list")
    @GetMapping("/list")
    public TableDataInfo list(CouponHistory couponHistory)
    {
        startPage();
        List<CouponHistory> list = couponHistoryService.selectCouponHistoryList(couponHistory);
        return getDataTable(list);
    }

    /**
     * 导出优惠券领取历史记录列表
     */
    @PreAuthorize(hasPermi = "coupon:couponHistory:export")
    @Log(title = "优惠券领取历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CouponHistory couponHistory) throws IOException
    {
        List<CouponHistory> list = couponHistoryService.selectCouponHistoryList(couponHistory);
        ExcelUtil<CouponHistory> util = new ExcelUtil<CouponHistory>(CouponHistory.class);
        util.exportExcel(response, list, "优惠券领取历史记录数据");
    }

    /**
     * 获取优惠券领取历史记录详细信息
     */
    @PreAuthorize(hasPermi = "coupon:couponHistory:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(couponHistoryService.selectCouponHistoryById(id));
    }

    /**
     * 新增优惠券领取历史记录
     */
    @PreAuthorize(hasPermi = "coupon:couponHistory:add")
    @Log(title = "优惠券领取历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CouponHistory couponHistory)
    {
        return toAjax(couponHistoryService.insertCouponHistory(couponHistory));
    }

    /**
     * 修改优惠券领取历史记录
     */
    @PreAuthorize(hasPermi = "coupon:couponHistory:edit")
    @Log(title = "优惠券领取历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CouponHistory couponHistory)
    {
        return toAjax(couponHistoryService.updateCouponHistory(couponHistory));
    }

    /**
     * 删除优惠券领取历史记录
     */
    @PreAuthorize(hasPermi = "coupon:couponHistory:remove")
    @Log(title = "优惠券领取历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(couponHistoryService.deleteCouponHistoryByIds(ids));
    }
}
