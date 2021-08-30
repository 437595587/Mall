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
import com.ruoyi.coupon.domain.SeckillPromotion;
import com.ruoyi.coupon.service.ISeckillPromotionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 秒杀活动Controller
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/seckillPromotion")
public class SeckillPromotionController extends BaseController
{
    @Autowired
    private ISeckillPromotionService seckillPromotionService;

    /**
     * 查询秒杀活动列表
     */
    @PreAuthorize(hasPermi = "coupon:seckillPromotion:list")
    @GetMapping("/list")
    public TableDataInfo list(SeckillPromotion seckillPromotion)
    {
        startPage();
        List<SeckillPromotion> list = seckillPromotionService.selectSeckillPromotionList(seckillPromotion);
        return getDataTable(list);
    }

    /**
     * 导出秒杀活动列表
     */
    @PreAuthorize(hasPermi = "coupon:seckillPromotion:export")
    @Log(title = "秒杀活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeckillPromotion seckillPromotion) throws IOException
    {
        List<SeckillPromotion> list = seckillPromotionService.selectSeckillPromotionList(seckillPromotion);
        ExcelUtil<SeckillPromotion> util = new ExcelUtil<SeckillPromotion>(SeckillPromotion.class);
        util.exportExcel(response, list, "秒杀活动数据");
    }

    /**
     * 获取秒杀活动详细信息
     */
    @PreAuthorize(hasPermi = "coupon:seckillPromotion:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(seckillPromotionService.selectSeckillPromotionById(id));
    }

    /**
     * 新增秒杀活动
     */
    @PreAuthorize(hasPermi = "coupon:seckillPromotion:add")
    @Log(title = "秒杀活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeckillPromotion seckillPromotion)
    {
        return toAjax(seckillPromotionService.insertSeckillPromotion(seckillPromotion));
    }

    /**
     * 修改秒杀活动
     */
    @PreAuthorize(hasPermi = "coupon:seckillPromotion:edit")
    @Log(title = "秒杀活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeckillPromotion seckillPromotion)
    {
        return toAjax(seckillPromotionService.updateSeckillPromotion(seckillPromotion));
    }

    /**
     * 删除秒杀活动
     */
    @PreAuthorize(hasPermi = "coupon:seckillPromotion:remove")
    @Log(title = "秒杀活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(seckillPromotionService.deleteSeckillPromotionByIds(ids));
    }
}
