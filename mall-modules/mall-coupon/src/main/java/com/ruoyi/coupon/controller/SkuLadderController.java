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
import com.ruoyi.coupon.domain.SkuLadder;
import com.ruoyi.coupon.service.ISkuLadderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 商品阶梯价格Controller
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/skuLadder")
public class SkuLadderController extends BaseController
{
    @Autowired
    private ISkuLadderService skuLadderService;

    /**
     * 查询商品阶梯价格列表
     */
    @PreAuthorize(hasPermi = "coupon:skuLadder:list")
    @GetMapping("/list")
    public TableDataInfo list(SkuLadder skuLadder)
    {
        startPage();
        List<SkuLadder> list = skuLadderService.selectSkuLadderList(skuLadder);
        return getDataTable(list);
    }

    /**
     * 导出商品阶梯价格列表
     */
    @PreAuthorize(hasPermi = "coupon:skuLadder:export")
    @Log(title = "商品阶梯价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuLadder skuLadder) throws IOException
    {
        List<SkuLadder> list = skuLadderService.selectSkuLadderList(skuLadder);
        ExcelUtil<SkuLadder> util = new ExcelUtil<SkuLadder>(SkuLadder.class);
        util.exportExcel(response, list, "商品阶梯价格数据");
    }

    /**
     * 获取商品阶梯价格详细信息
     */
    @PreAuthorize(hasPermi = "coupon:skuLadder:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(skuLadderService.selectSkuLadderById(id));
    }

    /**
     * 新增商品阶梯价格
     */
    @PreAuthorize(hasPermi = "coupon:skuLadder:add")
    @Log(title = "商品阶梯价格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuLadder skuLadder)
    {
        return toAjax(skuLadderService.insertSkuLadder(skuLadder));
    }

    /**
     * 修改商品阶梯价格
     */
    @PreAuthorize(hasPermi = "coupon:skuLadder:edit")
    @Log(title = "商品阶梯价格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuLadder skuLadder)
    {
        return toAjax(skuLadderService.updateSkuLadder(skuLadder));
    }

    /**
     * 删除商品阶梯价格
     */
    @PreAuthorize(hasPermi = "coupon:skuLadder:remove")
    @Log(title = "商品阶梯价格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skuLadderService.deleteSkuLadderByIds(ids));
    }
}
