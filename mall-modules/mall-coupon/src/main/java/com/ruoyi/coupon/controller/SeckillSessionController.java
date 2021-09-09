package com.ruoyi.coupon.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.coupon.domain.SeckillSession;
import com.ruoyi.coupon.service.ISeckillSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 秒杀活动场次Controller
 *
 * @author xuxing
 * @date 2021-08-23
 */
@RestController
@RequestMapping("/seckillSession")
public class SeckillSessionController extends BaseController
{
    @Autowired
    private ISeckillSessionService seckillSessionService;

    @GetMapping("/latest3DaysSession")
    public R<List<SeckillSession>> getLatest3DaysSession() {
        List<SeckillSession> seckillSessions = seckillSessionService.selectLatest3DaysSessionList();
        return R.ok(seckillSessions);
    }

    /**
     * 查询秒杀活动场次列表
     */
    @PreAuthorize(hasPermi = "coupon:seckillSession:list")
    @GetMapping("/list")
    public TableDataInfo list(SeckillSession seckillSession)
    {
        startPage();
        List<SeckillSession> list = seckillSessionService.selectSeckillSessionList(seckillSession);
        return getDataTable(list);
    }

    /**
     * 导出秒杀活动场次列表
     */
    @PreAuthorize(hasPermi = "coupon:seckillSession:export")
    @Log(title = "秒杀活动场次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeckillSession seckillSession) throws IOException
    {
        List<SeckillSession> list = seckillSessionService.selectSeckillSessionList(seckillSession);
        ExcelUtil<SeckillSession> util = new ExcelUtil<SeckillSession>(SeckillSession.class);
        util.exportExcel(response, list, "秒杀活动场次数据");
    }

    /**
     * 获取秒杀活动场次详细信息
     */
    @PreAuthorize(hasPermi = "coupon:seckillSession:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(seckillSessionService.selectSeckillSessionById(id));
    }

    /**
     * 新增秒杀活动场次
     */
    @PreAuthorize(hasPermi = "coupon:seckillSession:add")
    @Log(title = "秒杀活动场次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeckillSession seckillSession)
    {
        return toAjax(seckillSessionService.insertSeckillSession(seckillSession));
    }

    /**
     * 修改秒杀活动场次
     */
    @PreAuthorize(hasPermi = "coupon:seckillSession:edit")
    @Log(title = "秒杀活动场次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeckillSession seckillSession)
    {
        return toAjax(seckillSessionService.updateSeckillSession(seckillSession));
    }

    /**
     * 删除秒杀活动场次
     */
    @PreAuthorize(hasPermi = "coupon:seckillSession:remove")
    @Log(title = "秒杀活动场次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(seckillSessionService.deleteSeckillSessionByIds(ids));
    }
}
