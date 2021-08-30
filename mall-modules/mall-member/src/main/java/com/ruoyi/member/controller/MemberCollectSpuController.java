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
import com.ruoyi.member.domain.MemberCollectSpu;
import com.ruoyi.member.service.IMemberCollectSpuService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 会员收藏的商品Controller
 * 
 * @author xuxing
 * @date 2021-08-22
 */
@RestController
@RequestMapping("/memberCollectSpu")
public class MemberCollectSpuController extends BaseController
{
    @Autowired
    private IMemberCollectSpuService memberCollectSpuService;

    /**
     * 查询会员收藏的商品列表
     */
    @PreAuthorize(hasPermi = "member:memberCollectSpu:list")
    @GetMapping("/list")
    public TableDataInfo list(MemberCollectSpu memberCollectSpu)
    {
        startPage();
        List<MemberCollectSpu> list = memberCollectSpuService.selectMemberCollectSpuList(memberCollectSpu);
        return getDataTable(list);
    }

    /**
     * 导出会员收藏的商品列表
     */
    @PreAuthorize(hasPermi = "member:memberCollectSpu:export")
    @Log(title = "会员收藏的商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberCollectSpu memberCollectSpu) throws IOException
    {
        List<MemberCollectSpu> list = memberCollectSpuService.selectMemberCollectSpuList(memberCollectSpu);
        ExcelUtil<MemberCollectSpu> util = new ExcelUtil<MemberCollectSpu>(MemberCollectSpu.class);
        util.exportExcel(response, list, "会员收藏的商品数据");
    }

    /**
     * 获取会员收藏的商品详细信息
     */
    @PreAuthorize(hasPermi = "member:memberCollectSpu:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberCollectSpuService.selectMemberCollectSpuById(id));
    }

    /**
     * 新增会员收藏的商品
     */
    @PreAuthorize(hasPermi = "member:memberCollectSpu:add")
    @Log(title = "会员收藏的商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberCollectSpu memberCollectSpu)
    {
        return toAjax(memberCollectSpuService.insertMemberCollectSpu(memberCollectSpu));
    }

    /**
     * 修改会员收藏的商品
     */
    @PreAuthorize(hasPermi = "member:memberCollectSpu:edit")
    @Log(title = "会员收藏的商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberCollectSpu memberCollectSpu)
    {
        return toAjax(memberCollectSpuService.updateMemberCollectSpu(memberCollectSpu));
    }

    /**
     * 删除会员收藏的商品
     */
    @PreAuthorize(hasPermi = "member:memberCollectSpu:remove")
    @Log(title = "会员收藏的商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberCollectSpuService.deleteMemberCollectSpuByIds(ids));
    }
}
