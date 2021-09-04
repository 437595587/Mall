package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.to.SocialUser;
import com.ruoyi.mall.api.to.UserLoginTo;
import com.ruoyi.mall.api.to.UserRegisterTo;
import com.ruoyi.mall.api.vo.Member;
import com.ruoyi.mall.api.vo.MemberReceiveAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-member")
public interface RemoteMemberService {

    @PostMapping("/member/register")
    public R<Boolean> register(@RequestBody UserRegisterTo to);

    @PostMapping("/member/login")
    public R<Member> login(@RequestBody UserLoginTo to);

    @PostMapping("/member/oauth2/login")
    public R<Member> oAuth2Login(@RequestBody SocialUser socialUser);

    @GetMapping("/memberReceiveAddress/{memberId}/address")
    public R<List<MemberReceiveAddress>> getMemberAddress(@PathVariable("memberId") Long memberId);

    @GetMapping(value = "/memberReceiveAddress/{id}")
    public R<MemberReceiveAddress> getAddressInfo(@PathVariable("id") Long id);
}
