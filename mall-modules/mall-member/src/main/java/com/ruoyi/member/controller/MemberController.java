package com.ruoyi.member.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.BizCodeEnum;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.mall.api.to.SocialUser;
import com.ruoyi.mall.api.to.UserLoginTo;
import com.ruoyi.member.domain.Member;
import com.ruoyi.member.domain.vo.MemberRegisterVo;
import com.ruoyi.member.exception.PhoneExistException;
import com.ruoyi.member.exception.UsernameExistException;
import com.ruoyi.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 会员Controller
 *
 * @author xuxing
 * @date 2021-08-22
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController
{
    @Autowired
    private IMemberService memberService;

    @PostMapping("/oauth2/login")
    public R<Member> oAuth2Login(@RequestBody SocialUser socialUser) {
        Member member = memberService.login(socialUser);
        if (member != null) {
            return R.ok(member);
        } else {
            return R.fail(BizCodeEnum.LOGIN_ACCT_PASSWORD_INVALID_EXCEPTION.getCode().intValue(), BizCodeEnum.LOGIN_ACCT_PASSWORD_INVALID_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/login")
    public R<Member> login(@RequestBody UserLoginTo to) {
        Member member = memberService.login(to);
        if (member != null) {
            return R.ok(member);
        } else {
            return R.fail(BizCodeEnum.LOGIN_ACCT_PASSWORD_INVALID_EXCEPTION.getCode().intValue(), BizCodeEnum.LOGIN_ACCT_PASSWORD_INVALID_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/register")
    public R<Boolean> register(@RequestBody MemberRegisterVo vo) {
        try {
            return R.ok(memberService.register(vo));
        } catch (PhoneExistException e) {
            return R.fail(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode().intValue(), BizCodeEnum.PHONE_EXIST_EXCEPTION.getMsg());
        } catch (UsernameExistException e) {
            return R.fail(BizCodeEnum.USER_EXIST_EXCEPTION.getCode().intValue(), BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        }
    }

    /**
     * 查询会员列表
     */
    @PreAuthorize(hasPermi = "member:member:list")
    @GetMapping("/list")
    public TableDataInfo list(Member member)
    {
        startPage();
        List<Member> list = memberService.selectMemberList(member);
        return getDataTable(list);
    }

    /**
     * 导出会员列表
     */
    @PreAuthorize(hasPermi = "member:member:export")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Member member) throws IOException
    {
        List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        util.exportExcel(response, list, "会员数据");
    }

    /**
     * 获取会员详细信息
     */
    @PreAuthorize(hasPermi = "member:member:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(memberService.selectMemberById(id));
    }

    /**
     * 新增会员
     */
    @PreAuthorize(hasPermi = "member:member:add")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Member member)
    {
        return toAjax(memberService.insertMember(member));
    }

    /**
     * 修改会员
     */
    @PreAuthorize(hasPermi = "member:member:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Member member)
    {
        return toAjax(memberService.updateMember(member));
    }

    /**
     * 删除会员
     */
    @PreAuthorize(hasPermi = "member:member:remove")
    @Log(title = "会员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberService.deleteMemberByIds(ids));
    }
}
