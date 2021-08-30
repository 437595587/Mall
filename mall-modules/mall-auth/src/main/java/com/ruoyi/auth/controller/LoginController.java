package com.ruoyi.auth.controller;

import com.ruoyi.auth.constant.AuthConstant;
import com.ruoyi.auth.domain.vo.UserLoginVo;
import com.ruoyi.auth.domain.vo.UserRegisterVo;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.mall.api.RemoteMemberService;
import com.ruoyi.mall.api.to.UserLoginTo;
import com.ruoyi.mall.api.to.UserRegisterTo;
import com.ruoyi.mall.api.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RemoteMemberService remoteMemberService;

    /**
     * //TODO 重定向携带数据，利用session原理。将数据放在session中。
     *        只要跳到下一个页面取出这个数据以后，session里面的数据就会删除掉
     * //TODO 1、分布式下的session问题。
     *
     * redirectAttributes 模拟重定向携带数据
     */
    @PostMapping("/register")
    public String register(@Valid UserRegisterVo vo, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            //校验失败 去注册页
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                if (!errors.containsKey(fieldError.getField())) {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
            }
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.mall.com/register.html";
        }
        //1、校验验证码
        String code = vo.getCode();
        String s = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (StringUtils.isNotEmpty(s)) {
            if (code.equals(s.split("_")[0])) {
                //删除验证码
                redisTemplate.delete(AuthConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                //验证码通过 真正注册 远程服务
                UserRegisterTo to = new UserRegisterTo();
                BeanUtils.copyBeanProp(to, vo);
                R<Boolean> r = remoteMemberService.register(to);
                if (r.getCode() == 200) {
                    //成功
                    return "redirect:http://auth.mall.com/login.html";
                } else {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("msg", r.getMsg());
                    redirectAttributes.addFlashAttribute("errors", errors);
                    return "redirect:http://auth.mall.com/register.html";
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("code", "验证码错误");
                redirectAttributes.addFlashAttribute("errors", errors);
                return "redirect:http://auth.mall.com/register.html";
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("code", "验证码错误");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.mall.com/register.html";
        }
    }

    @GetMapping("/login.html")
    public String loginPage(HttpSession session) {
        Object attribute = session.getAttribute(AuthConstant.LOGIN_USER);
        if (attribute == null) {
            return "login";
        } else {
            return "redirect:http://mall.com";
        }
    }

    @PostMapping("/login")
    public String login(UserLoginVo vo, RedirectAttributes redirectAttributes, HttpSession session) {
        //远程登录
        UserLoginTo to = new UserLoginTo();
        BeanUtils.copyBeanProp(to, vo);
        R<Member> r = remoteMemberService.login(to);
        if (r.getCode() == 200) {
            //成功
            Member data = r.getData();
            session.setAttribute(AuthConstant.LOGIN_USER, data);
            return "redirect:http://mall.com";
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("msg", r.getMsg());
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.mall.com/login.html";
        }
    }
}
