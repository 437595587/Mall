package com.ruoyi.member.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.member.domain.MemberReceiveAddress;
import com.ruoyi.member.service.IMemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 会员收货地址Controller
 *
 * @author xuxing
 * @date 2021-08-22
 */
@RestController
@RequestMapping("/memberReceiveAddress")
public class MemberReceiveAddressController extends BaseController
{
    @Autowired
    private IMemberReceiveAddressService memberReceiveAddressService;

    /**
     * 查询会员收货地址列表
     */
    @PreAuthorize(hasPermi = "member:memberReceiveAddress:list")
    @GetMapping("/list")
    public TableDataInfo list(MemberReceiveAddress memberReceiveAddress)
    {
        startPage();
        List<MemberReceiveAddress> list = memberReceiveAddressService.selectMemberReceiveAddressList(memberReceiveAddress);
        return getDataTable(list);
    }

    /**
     * 导出会员收货地址列表
     */
    @PreAuthorize(hasPermi = "member:memberReceiveAddress:export")
    @Log(title = "会员收货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberReceiveAddress memberReceiveAddress) throws IOException
    {
        List<MemberReceiveAddress> list = memberReceiveAddressService.selectMemberReceiveAddressList(memberReceiveAddress);
        ExcelUtil<MemberReceiveAddress> util = new ExcelUtil<MemberReceiveAddress>(MemberReceiveAddress.class);
        util.exportExcel(response, list, "会员收货地址数据");
    }

    /**
     * 获取会员收货地址详细信息
     */
    @PreAuthorize(hasPermi = "member:memberReceiveAddress:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberReceiveAddressService.selectMemberReceiveAddressById(id));
    }

    /**
     * 新增会员收货地址
     */
    @PreAuthorize(hasPermi = "member:memberReceiveAddress:add")
    @Log(title = "会员收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberReceiveAddress memberReceiveAddress)
    {
        return toAjax(memberReceiveAddressService.insertMemberReceiveAddress(memberReceiveAddress));
    }

    /**
     * 修改会员收货地址
     */
    @PreAuthorize(hasPermi = "member:memberReceiveAddress:edit")
    @Log(title = "会员收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberReceiveAddress memberReceiveAddress)
    {
        return toAjax(memberReceiveAddressService.updateMemberReceiveAddress(memberReceiveAddress));
    }

    /**
     * 删除会员收货地址
     */
    @PreAuthorize(hasPermi = "member:memberReceiveAddress:remove")
    @Log(title = "会员收货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberReceiveAddressService.deleteMemberReceiveAddressByIds(ids));
    }
}
