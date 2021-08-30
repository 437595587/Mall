package com.ruoyi.coupon.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.coupon.domain.SkuFullReduction;
import com.ruoyi.coupon.service.ISkuFullReductionService;
import com.ruoyi.mall.api.to.SkuReductionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品满减信息Controller
 *
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/skuFullReduction")
public class SkuFullReductionController extends BaseController
{
    @Autowired
    private ISkuFullReductionService skuFullReductionService;

    /**
     * 保存商品的优惠信息
     */
    @PreAuthorize(hasPermi = "coupon:skuFullReduction:add")
    @Log(title = "商品的优惠信息", businessType = BusinessType.INSERT)
    @PostMapping("/addSkuSmsInfo")
    public R<Boolean> addSkuSmsInfo(@RequestBody SkuReductionTo skuReductionTo) {
        int i = skuFullReductionService.addSkuSmsInfo(skuReductionTo);
        if (i > 0) {
            return R.ok(true);
        } else {
            return R.fail(false);
        }
    }

    /**
     * 查询商品满减信息列表
     */
    @PreAuthorize(hasPermi = "coupon:skuFullReduction:list")
    @GetMapping("/list")
    public TableDataInfo list(SkuFullReduction skuFullReduction)
    {
        startPage();
        List<SkuFullReduction> list = skuFullReductionService.selectSkuFullReductionList(skuFullReduction);
        return getDataTable(list);
    }

    /**
     * 导出商品满减信息列表
     */
    @PreAuthorize(hasPermi = "coupon:skuFullReduction:export")
    @Log(title = "商品满减信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuFullReduction skuFullReduction) throws IOException
    {
        List<SkuFullReduction> list = skuFullReductionService.selectSkuFullReductionList(skuFullReduction);
        ExcelUtil<SkuFullReduction> util = new ExcelUtil<SkuFullReduction>(SkuFullReduction.class);
        util.exportExcel(response, list, "商品满减信息数据");
    }

    /**
     * 获取商品满减信息详细信息
     */
    @PreAuthorize(hasPermi = "coupon:skuFullReduction:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(skuFullReductionService.selectSkuFullReductionById(id));
    }

    /**
     * 新增商品满减信息
     */
    @PreAuthorize(hasPermi = "coupon:skuFullReduction:add")
    @Log(title = "商品满减信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuFullReduction skuFullReduction)
    {
        return toAjax(skuFullReductionService.insertSkuFullReduction(skuFullReduction));
    }

    /**
     * 修改商品满减信息
     */
    @PreAuthorize(hasPermi = "coupon:skuFullReduction:edit")
    @Log(title = "商品满减信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuFullReduction skuFullReduction)
    {
        return toAjax(skuFullReductionService.updateSkuFullReduction(skuFullReduction));
    }

    /**
     * 删除商品满减信息
     */
    @PreAuthorize(hasPermi = "coupon:skuFullReduction:remove")
    @Log(title = "商品满减信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skuFullReductionService.deleteSkuFullReductionByIds(ids));
    }
}
