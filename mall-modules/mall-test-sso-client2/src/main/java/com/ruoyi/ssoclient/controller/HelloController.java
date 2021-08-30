package com.ruoyi.ssoclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @Value("${sso.server.url}")
    String ssoServerUrl;

    @Autowired
    RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/boss")
    public String employees(Model model, HttpSession session, @RequestParam(value = "token",required = false) String token) {
        if (token != null && !token.equals("")) {
            // TODO 去sso服务器获取当前token真正对应的用户信息
            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://ssoserver.com:6789/userInfo?token=" + token, String.class);
            String body = responseEntity.getBody();
            session.setAttribute("loginUser", body);
        }
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) {
            //跳转到登录服务器 登录

            return "redirect:" + ssoServerUrl + "?redirect_url=http://client2.com:8082/boss";
        } else {
            List<String> boss = new ArrayList<>();
            boss.add("张三");
            boss.add("李四");
            model.addAttribute("boss", boss);
            return "list";
        }
    }
}
