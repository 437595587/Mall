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
import com.ruoyi.coupon.domain.SeckillSkuRelation;
import com.ruoyi.coupon.service.ISeckillSkuRelationService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 秒杀活动商品关联Controller
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/seckillSkuRelation")
public class SeckillSkuRelationController extends BaseController
{
    @Autowired
    private ISeckillSkuRelationService seckillSkuRelationService;

    /**
     * 查询秒杀活动商品关联列表
     */
    @PreAuthorize(hasPermi = "coupon:seckillSkuRelation:list")
    @GetMapping("/list")
    public TableDataInfo list(SeckillSkuRelation seckillSkuRelation)
    {
        startPage();
        List<SeckillSkuRelation> list = seckillSkuRelationService.selectSeckillSkuRelationList(seckillSkuRelation);
        return getDataTable(list);
    }

    /**
     * 导出秒杀活动商品关联列表
     */
    @PreAuthorize(hasPermi = "coupon:seckillSkuRelation:export")
    @Log(title = "秒杀活动商品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeckillSkuRelation seckillSkuRelation) throws IOException
    {
        List<SeckillSkuRelation> list = seckillSkuRelationService.selectSeckillSkuRelationList(seckillSkuRelation);
        ExcelUtil<SeckillSkuRelation> util = new ExcelUtil<SeckillSkuRelation>(SeckillSkuRelation.class);
        util.exportExcel(response, list, "秒杀活动商品关联数据");
    }

    /**
     * 获取秒杀活动商品关联详细信息
     */
    @PreAuthorize(hasPermi = "coupon:seckillSkuRelation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(seckillSkuRelationService.selectSeckillSkuRelationById(id));
    }

    /**
     * 新增秒杀活动商品关联
     */
    @PreAuthorize(hasPermi = "coupon:seckillSkuRelation:add")
    @Log(title = "秒杀活动商品关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeckillSkuRelation seckillSkuRelation)
    {
        return toAjax(seckillSkuRelationService.insertSeckillSkuRelation(seckillSkuRelation));
    }

    /**
     * 修改秒杀活动商品关联
     */
    @PreAuthorize(hasPermi = "coupon:seckillSkuRelation:edit")
    @Log(title = "秒杀活动商品关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeckillSkuRelation seckillSkuRelation)
    {
        return toAjax(seckillSkuRelationService.updateSeckillSkuRelation(seckillSkuRelation));
    }

    /**
     * 删除秒杀活动商品关联
     */
    @PreAuthorize(hasPermi = "coupon:seckillSkuRelation:remove")
    @Log(title = "秒杀活动商品关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(seckillSkuRelationService.deleteSeckillSkuRelationByIds(ids));
    }
}
