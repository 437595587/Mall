package com.ruoyi.product.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsBrand;
import com.ruoyi.product.service.IPmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController extends BaseController
{
    @Autowired
    private IPmsBrandService pmsBrandService;

    /**
     * 查询品牌列表
     */
    @PreAuthorize(hasPermi = "product:brand:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsBrand pmsBrand)
    {
        startPage();
        List<PmsBrand> list = pmsBrandService.selectPmsBrandList(pmsBrand);
        return getDataTable(list);
    }

    /**
     * 导出品牌列表
     */
    @PreAuthorize(hasPermi = "product:brand:export")
    @Log(title = "品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsBrand pmsBrand) throws IOException
    {
        List<PmsBrand> list = pmsBrandService.selectPmsBrandList(pmsBrand);
        ExcelUtil<PmsBrand> util = new ExcelUtil<PmsBrand>(PmsBrand.class);
        util.exportExcel(response, list, "品牌数据");
    }

    /**
     * 获取品牌详细信息
     */
    @PreAuthorize(hasPermi = "product:brand:query")
    @GetMapping(value = "/{brandId}")
    public AjaxResult getInfo(@PathVariable("brandId") Long brandId)
    {
        return AjaxResult.success(pmsBrandService.selectPmsBrandByBrandId(brandId));
    }

    /**
     * 新增品牌
     */
    @PreAuthorize(hasPermi = "product:brand:add")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody PmsBrand pmsBrand, BindingResult result)
    {
        //1、获取校验的错误结果
        if (result.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            result.getFieldErrors().forEach(item -> {
                //获取到错误提示
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            return AjaxResult.error(400, "提交的数据不合法").put("data", map);
        } else {
            return toAjax(pmsBrandService.insertPmsBrand(pmsBrand));
        }
    }

    /**
     * 修改品牌
     */
    @PreAuthorize(hasPermi = "product:brand:edit")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsBrand pmsBrand)
    {
        return toAjax(pmsBrandService.updatePmsBrand(pmsBrand));
    }

    /**
     * 删除品牌
     */
    @PreAuthorize(hasPermi = "product:brand:remove")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{brandIds}")
    public AjaxResult remove(@PathVariable Long[] brandIds)
    {
        return toAjax(pmsBrandService.deletePmsBrandByBrandIds(brandIds));
    }
}
