package com.ruoyi.product.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsSpuInfo;
import com.ruoyi.product.service.IPmsSpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * spu信息Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/spuInfo")
public class PmsSpuInfoController extends BaseController
{
    @Autowired
    private IPmsSpuInfoService pmsSpuInfoService;

    /**
     * 查询spu信息列表
     */
    @PreAuthorize(hasPermi = "product:info:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSpuInfo pmsSpuInfo)
    {
        startPage();
        List<PmsSpuInfo> list = pmsSpuInfoService.selectPmsSpuInfoList(pmsSpuInfo);
        return getDataTable(list);
    }

    /**
     * 导出spu信息列表
     */
    @PreAuthorize(hasPermi = "product:info:export")
    @Log(title = "spu信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSpuInfo pmsSpuInfo) throws IOException
    {
        List<PmsSpuInfo> list = pmsSpuInfoService.selectPmsSpuInfoList(pmsSpuInfo);
        ExcelUtil<PmsSpuInfo> util = new ExcelUtil<PmsSpuInfo>(PmsSpuInfo.class);
        util.exportExcel(response, list, "spu信息数据");
    }

    /**
     * 获取spu信息详细信息
     */
    @PreAuthorize(hasPermi = "product:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pmsSpuInfoService.selectPmsSpuInfoById(id));
    }

    /**
     * 新增spu信息
     */
    @PreAuthorize(hasPermi = "product:info:add")
    @Log(title = "spu信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSpuInfo pmsSpuInfo)
    {
        return toAjax(pmsSpuInfoService.insertPmsSpuInfo(pmsSpuInfo));
    }

    /**
     * 修改spu信息
     */
    @PreAuthorize(hasPermi = "product:info:edit")
    @Log(title = "spu信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSpuInfo pmsSpuInfo)
    {
        return toAjax(pmsSpuInfoService.updatePmsSpuInfo(pmsSpuInfo));
    }

    /**
     * 删除spu信息
     */
    @PreAuthorize(hasPermi = "product:info:remove")
    @Log(title = "spu信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSpuInfoService.deletePmsSpuInfoByIds(ids));
    }
}
