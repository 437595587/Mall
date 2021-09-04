package com.ruoyi.ware.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.ware.domain.WareInfo;
import com.ruoyi.ware.domain.vo.FareVo;
import com.ruoyi.ware.service.IWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 仓库信息Controller
 *
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/wareInfo")
public class WareInfoController extends BaseController
{
    @Autowired
    private IWareInfoService wareInfoService;

    @GetMapping("/fare")
    public R<FareVo> getFare(@RequestParam("addrId") Long addrId) {
        FareVo fareVo = wareInfoService.selectFare(addrId);
        return R.ok(fareVo);
    }

    /**
     * 查询仓库信息列表
     */
    @PreAuthorize(hasPermi = "ware:wareInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(WareInfo wareInfo)
    {
        startPage();
        List<WareInfo> list = wareInfoService.selectWareInfoList(wareInfo);
        return getDataTable(list);
    }

    /**
     * 导出仓库信息列表
     */
    @PreAuthorize(hasPermi = "ware:wareInfo:export")
    @Log(title = "仓库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareInfo wareInfo) throws IOException
    {
        List<WareInfo> list = wareInfoService.selectWareInfoList(wareInfo);
        ExcelUtil<WareInfo> util = new ExcelUtil<WareInfo>(WareInfo.class);
        util.exportExcel(response, list, "仓库信息数据");
    }

    /**
     * 获取仓库信息详细信息
     */
    @PreAuthorize(hasPermi = "ware:wareInfo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wareInfoService.selectWareInfoById(id));
    }

    /**
     * 新增仓库信息
     */
    @PreAuthorize(hasPermi = "ware:wareInfo:add")
    @Log(title = "仓库信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WareInfo wareInfo)
    {
        return toAjax(wareInfoService.insertWareInfo(wareInfo));
    }

    /**
     * 修改仓库信息
     */
    @PreAuthorize(hasPermi = "ware:wareInfo:edit")
    @Log(title = "仓库信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WareInfo wareInfo)
    {
        return toAjax(wareInfoService.updateWareInfo(wareInfo));
    }

    /**
     * 删除仓库信息
     */
    @PreAuthorize(hasPermi = "ware:wareInfo:remove")
    @Log(title = "仓库信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wareInfoService.deleteWareInfoByIds(ids));
    }
}
