package com.ruoyi.product.controller;

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
import com.ruoyi.product.domain.PmsCommentReplay;
import com.ruoyi.product.service.IPmsCommentReplayService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 商品评价回复关系Controller
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/replay")
public class PmsCommentReplayController extends BaseController
{
    @Autowired
    private IPmsCommentReplayService pmsCommentReplayService;

    /**
     * 查询商品评价回复关系列表
     */
    @PreAuthorize(hasPermi = "product:replay:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsCommentReplay pmsCommentReplay)
    {
        startPage();
        List<PmsCommentReplay> list = pmsCommentReplayService.selectPmsCommentReplayList(pmsCommentReplay);
        return getDataTable(list);
    }

    /**
     * 导出商品评价回复关系列表
     */
    @PreAuthorize(hasPermi = "product:replay:export")
    @Log(title = "商品评价回复关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsCommentReplay pmsCommentReplay) throws IOException
    {
        List<PmsCommentReplay> list = pmsCommentReplayService.selectPmsCommentReplayList(pmsCommentReplay);
        ExcelUtil<PmsCommentReplay> util = new ExcelUtil<PmsCommentReplay>(PmsCommentReplay.class);
        util.exportExcel(response, list, "商品评价回复关系数据");
    }

    /**
     * 获取商品评价回复关系详细信息
     */
    @PreAuthorize(hasPermi = "product:replay:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pmsCommentReplayService.selectPmsCommentReplayById(id));
    }

    /**
     * 新增商品评价回复关系
     */
    @PreAuthorize(hasPermi = "product:replay:add")
    @Log(title = "商品评价回复关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsCommentReplay pmsCommentReplay)
    {
        return toAjax(pmsCommentReplayService.insertPmsCommentReplay(pmsCommentReplay));
    }

    /**
     * 修改商品评价回复关系
     */
    @PreAuthorize(hasPermi = "product:replay:edit")
    @Log(title = "商品评价回复关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsCommentReplay pmsCommentReplay)
    {
        return toAjax(pmsCommentReplayService.updatePmsCommentReplay(pmsCommentReplay));
    }

    /**
     * 删除商品评价回复关系
     */
    @PreAuthorize(hasPermi = "product:replay:remove")
    @Log(title = "商品评价回复关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsCommentReplayService.deletePmsCommentReplayByIds(ids));
    }
}
