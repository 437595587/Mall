package com.ruoyi.product.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.product.domain.PmsAttr;
import com.ruoyi.product.domain.vo.PmsAttrReqVo;
import com.ruoyi.product.domain.vo.PmsAttrRespVo;
import com.ruoyi.product.service.IPmsAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品属性Controller
 *
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/attr")
public class PmsAttrController extends BaseController
{
    @Autowired
    private IPmsAttrService pmsAttrService;

    /**
     * 查询商品属性列表
     */
    @PreAuthorize(hasPermi = "product:attr:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsAttr pmsAttr)
    {
        startPage();
        List<PmsAttr> list = pmsAttrService.selectPmsAttrList(pmsAttr);
        return getDataTable(list);
    }

    /**
     * 导出商品属性列表
     */
    @PreAuthorize(hasPermi = "product:attr:export")
    @Log(title = "商品属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsAttr pmsAttr) throws IOException
    {
        List<PmsAttr> list = pmsAttrService.selectPmsAttrList(pmsAttr);
        ExcelUtil<PmsAttr> util = new ExcelUtil<PmsAttr>(PmsAttr.class);
        util.exportExcel(response, list, "商品属性数据");
    }

    /**
     * 获取商品属性详细信息
     */
//    @PreAuthorize(hasPermi = "product:attr:query")
    @GetMapping(value = "/{attrId}")
    public R<PmsAttrRespVo> getInfo(@PathVariable("attrId") Long attrId)
    {
        return R.ok(pmsAttrService.selectPmsAttrByAttrId(attrId));
    }

    /**
     * 新增商品属性
     */
    @PreAuthorize(hasPermi = "product:attr:add")
    @Log(title = "商品属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsAttrReqVo pmsAttrReqVo)
    {
        return toAjax(pmsAttrService.insertPmsAttr(pmsAttrReqVo));
    }

    /**
     * 修改商品属性
     */
    @PreAuthorize(hasPermi = "product:attr:edit")
    @Log(title = "商品属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsAttrReqVo pmsAttrReqVo)
    {
        return toAjax(pmsAttrService.updatePmsAttr(pmsAttrReqVo));
    }

    /**
     * 删除商品属性
     */
    @PreAuthorize(hasPermi = "product:attr:remove")
    @Log(title = "商品属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrIds}")
    public AjaxResult remove(@PathVariable Long[] attrIds)
    {
        return toAjax(pmsAttrService.deletePmsAttrByAttrIds(attrIds));
    }
}
