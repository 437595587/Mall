package com.ruoyi.ware.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.BizCodeEnum;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.mall.api.to.WareSkuLockTo;
import com.ruoyi.ware.domain.WareSku;
import com.ruoyi.ware.domain.vo.SkuHasStockVo;
import com.ruoyi.ware.service.IWareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品库存Controller
 *
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/wareSku")
public class WareSkuController extends BaseController
{
    @Autowired
    private IWareSkuService wareSkuService;

    @PostMapping("/lock/order")
    public R<Boolean> orderLockStock(@RequestBody WareSkuLockTo to) {
        try {
            boolean result = wareSkuService.orderLockStock(to);
            return R.ok(result);
        } catch (Exception e) {
            return R.fail(BizCodeEnum.NO_STOCK_EXCEPTION.getCode().intValue(), BizCodeEnum.NO_STOCK_EXCEPTION.getMsg());
        }
    }

    //查询sku是否有库存
    @PostMapping("/hasStock")
    public R<List<SkuHasStockVo>> getSkusHasStock(@RequestBody List<Long> skuIds) {
        List<SkuHasStockVo> vos = wareSkuService.selectSkusHasStock(skuIds);
        return R.ok(vos);
    }

    /**
     * 查询商品库存列表
     */
    @PreAuthorize(hasPermi = "ware:wareSku:list")
    @GetMapping("/list")
    public TableDataInfo list(WareSku wareSku)
    {
        startPage();
        List<WareSku> list = wareSkuService.selectWareSkuList(wareSku);
        return getDataTable(list);
    }

    /**
     * 导出商品库存列表
     */
    @PreAuthorize(hasPermi = "ware:wareSku:export")
    @Log(title = "商品库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareSku wareSku) throws IOException
    {
        List<WareSku> list = wareSkuService.selectWareSkuList(wareSku);
        ExcelUtil<WareSku> util = new ExcelUtil<WareSku>(WareSku.class);
        util.exportExcel(response, list, "商品库存数据");
    }

    /**
     * 获取商品库存详细信息
     */
    @PreAuthorize(hasPermi = "ware:wareSku:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wareSkuService.selectWareSkuById(id));
    }

    /**
     * 新增商品库存
     */
    @PreAuthorize(hasPermi = "ware:wareSku:add")
    @Log(title = "商品库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WareSku wareSku)
    {
        return toAjax(wareSkuService.insertWareSku(wareSku));
    }

    /**
     * 修改商品库存
     */
    @PreAuthorize(hasPermi = "ware:wareSku:edit")
    @Log(title = "商品库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WareSku wareSku)
    {
        return toAjax(wareSkuService.updateWareSku(wareSku));
    }

    /**
     * 删除商品库存
     */
    @PreAuthorize(hasPermi = "ware:wareSku:remove")
    @Log(title = "商品库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wareSkuService.deleteWareSkuByIds(ids));
    }
}
