package com.ruoyi.coupon.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.coupon.domain.SpuBounds;
import com.ruoyi.coupon.service.ISpuBoundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品spu积分设置Controller
 *
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/spuBounds")
public class SpuBoundsController extends BaseController
{
    @Autowired
    private ISpuBoundsService spuBoundsService;

    /**
     * 查询商品spu积分设置列表
     */
    @PreAuthorize(hasPermi = "coupon:spuBounds:list")
    @GetMapping("/list")
    public TableDataInfo list(SpuBounds spuBounds)
    {
        startPage();
        List<SpuBounds> list = spuBoundsService.selectSpuBoundsList(spuBounds);
        return getDataTable(list);
    }

    /**
     * 导出商品spu积分设置列表
     */
    @PreAuthorize(hasPermi = "coupon:spuBounds:export")
    @Log(title = "商品spu积分设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuBounds spuBounds) throws IOException
    {
        List<SpuBounds> list = spuBoundsService.selectSpuBoundsList(spuBounds);
        ExcelUtil<SpuBounds> util = new ExcelUtil<SpuBounds>(SpuBounds.class);
        util.exportExcel(response, list, "商品spu积分设置数据");
    }

    /**
     * 获取商品spu积分设置详细信息
     */
    @PreAuthorize(hasPermi = "coupon:spuBounds:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spuBoundsService.selectSpuBoundsById(id));
    }

    /**
     * 新增商品spu积分设置
     */
    @PreAuthorize(hasPermi = "coupon:spuBounds:add")
    @Log(title = "商品spu积分设置", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Boolean> add(@RequestBody SpuBounds spuBounds)
    {
        int i = spuBoundsService.insertSpuBounds(spuBounds);
        if (i > 0) {
            return R.ok(true);
        } else {
            return R.fail(false);
        }
    }

    /**
     * 修改商品spu积分设置
     */
    @PreAuthorize(hasPermi = "coupon:spuBounds:edit")
    @Log(title = "商品spu积分设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuBounds spuBounds)
    {
        return toAjax(spuBoundsService.updateSpuBounds(spuBounds));
    }

    /**
     * 删除商品spu积分设置
     */
    @PreAuthorize(hasPermi = "coupon:spuBounds:remove")
    @Log(title = "商品spu积分设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spuBoundsService.deleteSpuBoundsByIds(ids));
    }
}
