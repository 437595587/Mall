package com.ruoyi.ssoserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @ResponseBody
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("token") String token) {
        return redisTemplate.opsForValue().get(token);
    }

    @GetMapping("/login.html")
    public String loginPage(@RequestParam("redirect_url") String redirectUrl, Model model,
                            @CookieValue(value = "sso_token", required = false) String ssoToken) {
        if (ssoToken != null && !ssoToken.equals("")) {
            //说明有人登录过 浏览器留下痕迹
            return "redirect:" + redirectUrl + "?token=" + ssoToken;
        }
        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("redirectUrl") String redirectUrl,
                          HttpServletResponse response) {
        if (username.equals("admin") && password.equals("123456")) {
            //登录成功
            String uuid = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(uuid, username);
            response.addCookie(new Cookie("sso_token", uuid));
            return "redirect:" + redirectUrl + "?token=" + uuid;
        } else {
            //登录失败 跳回到之前的页面
            return "login";
        }
    }
}
