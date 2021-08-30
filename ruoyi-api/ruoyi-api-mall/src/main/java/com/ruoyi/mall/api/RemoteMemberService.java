package com.ruoyi.mall.api;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.to.SocialUser;
import com.ruoyi.mall.api.to.UserLoginTo;
import com.ruoyi.mall.api.to.UserRegisterTo;
import com.ruoyi.mall.api.vo.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mall-member")
public interface RemoteMemberService {

    @PostMapping("/member/register")
    public R<Boolean> register(@RequestBody UserRegisterTo to);

    @PostMapping("/member/login")
    public R<Member> login(@RequestBody UserLoginTo to);

    @PostMapping("member/oauth2/login")
    public R<Member> oAuth2Login(@RequestBody SocialUser socialUser);
}
