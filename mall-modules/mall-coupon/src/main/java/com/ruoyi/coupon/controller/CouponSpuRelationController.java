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
import com.ruoyi.coupon.domain.CouponSpuRelation;
import com.ruoyi.coupon.service.ICouponSpuRelationService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 优惠券与产品关联Controller
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/couponSpuRelation")
public class CouponSpuRelationController extends BaseController
{
    @Autowired
    private ICouponSpuRelationService couponSpuRelationService;

    /**
     * 查询优惠券与产品关联列表
     */
    @PreAuthorize(hasPermi = "coupon:couponSpuRelation:list")
    @GetMapping("/list")
    public TableDataInfo list(CouponSpuRelation couponSpuRelation)
    {
        startPage();
        List<CouponSpuRelation> list = couponSpuRelationService.selectCouponSpuRelationList(couponSpuRelation);
        return getDataTable(list);
    }

    /**
     * 导出优惠券与产品关联列表
     */
    @PreAuthorize(hasPermi = "coupon:couponSpuRelation:export")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CouponSpuRelation couponSpuRelation) throws IOException
    {
        List<CouponSpuRelation> list = couponSpuRelationService.selectCouponSpuRelationList(couponSpuRelation);
        ExcelUtil<CouponSpuRelation> util = new ExcelUtil<CouponSpuRelation>(CouponSpuRelation.class);
        util.exportExcel(response, list, "优惠券与产品关联数据");
    }

    /**
     * 获取优惠券与产品关联详细信息
     */
    @PreAuthorize(hasPermi = "coupon:couponSpuRelation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(couponSpuRelationService.selectCouponSpuRelationById(id));
    }

    /**
     * 新增优惠券与产品关联
     */
    @PreAuthorize(hasPermi = "coupon:couponSpuRelation:add")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CouponSpuRelation couponSpuRelation)
    {
        return toAjax(couponSpuRelationService.insertCouponSpuRelation(couponSpuRelation));
    }

    /**
     * 修改优惠券与产品关联
     */
    @PreAuthorize(hasPermi = "coupon:couponSpuRelation:edit")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CouponSpuRelation couponSpuRelation)
    {
        return toAjax(couponSpuRelationService.updateCouponSpuRelation(couponSpuRelation));
    }

    /**
     * 删除优惠券与产品关联
     */
    @PreAuthorize(hasPermi = "coupon:couponSpuRelation:remove")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(couponSpuRelationService.deleteCouponSpuRelationByIds(ids));
    }
}
