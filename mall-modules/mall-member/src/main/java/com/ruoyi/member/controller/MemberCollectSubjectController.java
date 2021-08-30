package com.ruoyi.member.controller;

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
import com.ruoyi.member.domain.MemberCollectSubject;
import com.ruoyi.member.service.IMemberCollectSubjectService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 会员收藏的专题活动Controller
 * 
 * @author xuxing
 * @date 2021-08-22
 */
@RestController
@RequestMapping("/memberCollectSubject")
public class MemberCollectSubjectController extends BaseController
{
    @Autowired
    private IMemberCollectSubjectService memberCollectSubjectService;

    /**
     * 查询会员收藏的专题活动列表
     */
    @PreAuthorize(hasPermi = "member:memberCollectSubject:list")
    @GetMapping("/list")
    public TableDataInfo list(MemberCollectSubject memberCollectSubject)
    {
        startPage();
        List<MemberCollectSubject> list = memberCollectSubjectService.selectMemberCollectSubjectList(memberCollectSubject);
        return getDataTable(list);
    }

    /**
     * 导出会员收藏的专题活动列表
     */
    @PreAuthorize(hasPermi = "member:memberCollectSubject:export")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberCollectSubject memberCollectSubject) throws IOException
    {
        List<MemberCollectSubject> list = memberCollectSubjectService.selectMemberCollectSubjectList(memberCollectSubject);
        ExcelUtil<MemberCollectSubject> util = new ExcelUtil<MemberCollectSubject>(MemberCollectSubject.class);
        util.exportExcel(response, list, "会员收藏的专题活动数据");
    }

    /**
     * 获取会员收藏的专题活动详细信息
     */
    @PreAuthorize(hasPermi = "member:memberCollectSubject:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberCollectSubjectService.selectMemberCollectSubjectById(id));
    }

    /**
     * 新增会员收藏的专题活动
     */
    @PreAuthorize(hasPermi = "member:memberCollectSubject:add")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberCollectSubject memberCollectSubject)
    {
        return toAjax(memberCollectSubjectService.insertMemberCollectSubject(memberCollectSubject));
    }

    /**
     * 修改会员收藏的专题活动
     */
    @PreAuthorize(hasPermi = "member:memberCollectSubject:edit")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberCollectSubject memberCollectSubject)
    {
        return toAjax(memberCollectSubjectService.updateMemberCollectSubject(memberCollectSubject));
    }

    /**
     * 删除会员收藏的专题活动
     */
    @PreAuthorize(hasPermi = "member:memberCollectSubject:remove")
    @Log(title = "会员收藏的专题活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberCollectSubjectService.deleteMemberCollectSubjectByIds(ids));
    }
}
