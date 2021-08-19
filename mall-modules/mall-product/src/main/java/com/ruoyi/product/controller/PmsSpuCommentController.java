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
import com.ruoyi.product.domain.PmsSpuComment;
import com.ruoyi.product.service.IPmsSpuCommentService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 商品评价Controller
 * 
 * @author xuxing
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/comment")
public class PmsSpuCommentController extends BaseController
{
    @Autowired
    private IPmsSpuCommentService pmsSpuCommentService;

    /**
     * 查询商品评价列表
     */
    @PreAuthorize(hasPermi = "product:comment:list")
    @GetMapping("/list")
    public TableDataInfo list(PmsSpuComment pmsSpuComment)
    {
        startPage();
        List<PmsSpuComment> list = pmsSpuCommentService.selectPmsSpuCommentList(pmsSpuComment);
        return getDataTable(list);
    }

    /**
     * 导出商品评价列表
     */
    @PreAuthorize(hasPermi = "product:comment:export")
    @Log(title = "商品评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSpuComment pmsSpuComment) throws IOException
    {
        List<PmsSpuComment> list = pmsSpuCommentService.selectPmsSpuCommentList(pmsSpuComment);
        ExcelUtil<PmsSpuComment> util = new ExcelUtil<PmsSpuComment>(PmsSpuComment.class);
        util.exportExcel(response, list, "商品评价数据");
    }

    /**
     * 获取商品评价详细信息
     */
    @PreAuthorize(hasPermi = "product:comment:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pmsSpuCommentService.selectPmsSpuCommentById(id));
    }

    /**
     * 新增商品评价
     */
    @PreAuthorize(hasPermi = "product:comment:add")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSpuComment pmsSpuComment)
    {
        return toAjax(pmsSpuCommentService.insertPmsSpuComment(pmsSpuComment));
    }

    /**
     * 修改商品评价
     */
    @PreAuthorize(hasPermi = "product:comment:edit")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSpuComment pmsSpuComment)
    {
        return toAjax(pmsSpuCommentService.updatePmsSpuComment(pmsSpuComment));
    }

    /**
     * 删除商品评价
     */
    @PreAuthorize(hasPermi = "product:comment:remove")
    @Log(title = "商品评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSpuCommentService.deletePmsSpuCommentByIds(ids));
    }
}
