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
import com.ruoyi.coupon.domain.HomeAdv;
import com.ruoyi.coupon.service.IHomeAdvService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 首页轮播广告Controller
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/homeAdv")
public class HomeAdvController extends BaseController
{
    @Autowired
    private IHomeAdvService homeAdvService;

    /**
     * 查询首页轮播广告列表
     */
    @PreAuthorize(hasPermi = "coupon:homeAdv:list")
    @GetMapping("/list")
    public TableDataInfo list(HomeAdv homeAdv)
    {
        startPage();
        List<HomeAdv> list = homeAdvService.selectHomeAdvList(homeAdv);
        return getDataTable(list);
    }

    /**
     * 导出首页轮播广告列表
     */
    @PreAuthorize(hasPermi = "coupon:homeAdv:export")
    @Log(title = "首页轮播广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeAdv homeAdv) throws IOException
    {
        List<HomeAdv> list = homeAdvService.selectHomeAdvList(homeAdv);
        ExcelUtil<HomeAdv> util = new ExcelUtil<HomeAdv>(HomeAdv.class);
        util.exportExcel(response, list, "首页轮播广告数据");
    }

    /**
     * 获取首页轮播广告详细信息
     */
    @PreAuthorize(hasPermi = "coupon:homeAdv:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(homeAdvService.selectHomeAdvById(id));
    }

    /**
     * 新增首页轮播广告
     */
    @PreAuthorize(hasPermi = "coupon:homeAdv:add")
    @Log(title = "首页轮播广告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeAdv homeAdv)
    {
        return toAjax(homeAdvService.insertHomeAdv(homeAdv));
    }

    /**
     * 修改首页轮播广告
     */
    @PreAuthorize(hasPermi = "coupon:homeAdv:edit")
    @Log(title = "首页轮播广告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeAdv homeAdv)
    {
        return toAjax(homeAdvService.updateHomeAdv(homeAdv));
    }

    /**
     * 删除首页轮播广告
     */
    @PreAuthorize(hasPermi = "coupon:homeAdv:remove")
    @Log(title = "首页轮播广告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homeAdvService.deleteHomeAdvByIds(ids));
    }
}
