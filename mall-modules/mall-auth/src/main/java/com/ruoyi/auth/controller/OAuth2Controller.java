package com.ruoyi.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.auth.constant.AuthConstant;
import com.ruoyi.auth.utils.HttpUtils;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.mall.api.RemoteMemberService;
import com.ruoyi.mall.api.to.SocialUser;
import com.ruoyi.mall.api.vo.Member;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OAuth2Controller {

    @Autowired
    RemoteMemberService remoteMemberService;

    @GetMapping("/success")
    public String gitee(@RequestParam("code") String code, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
        //1、根据code换取token
        Map<String, String> bodys = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        bodys.put("grant_type", "authorization_code");
        bodys.put("code", code);
        bodys.put("client_id", "277433b0b9023d9e3fe8cffc8dcabb1b88093c6d98a134af058bb87cf59e3b72");
        bodys.put("redirect_uri", "http://auth.mall.com/success");
        bodys.put("client_secret", "c8cebb615da19fe836ac6a512110bd74f20e6c2733ef6cf4d8c876346d1a4538");
        HttpResponse response = HttpUtils.doPost("https://gitee.com", "/oauth/token", "post", headers, null, bodys);
        if (response.getStatusLine().getStatusCode() == 200) {
            String json = EntityUtils.toString(response.getEntity());
            String access_token = JSONObject.parseObject(json).getString("access_token");
            Map<String, String> querys = new HashMap<>();
            querys.put("access_token", access_token);
            // https://gitee.com/api/v5/user?access_token={access_token}
            HttpResponse userInfoResponse = HttpUtils.doGet("https://gitee.com", "/api/v5/user", "get", headers, querys);
            if (userInfoResponse.getStatusLine().getStatusCode() == 200) {
                String userInfoJson = EntityUtils.toString(userInfoResponse.getEntity());
                SocialUser socialUser = JSON.parseObject(userInfoJson, SocialUser.class);
                R<Member> r = remoteMemberService.oAuth2Login(socialUser);
                if (r.getCode() == 200) {
                    Member data = r.getData();
                    session.setAttribute(AuthConstant.LOGIN_USER, data);
                    return "redirect:http://mall.com";
                } else {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("msg", r.getMsg());
                    redirectAttributes.addFlashAttribute("errors", errors);
                    return "redirect:http://auth.mall.com/login.html";
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("msg", "第三方服务授权失败");
                redirectAttributes.addFlashAttribute("errors", errors);
                return "redirect:http://auth.mall.com/login.html";
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("msg", "第三方服务授权失败");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.mall.com/login.html";
        }
    }
}
