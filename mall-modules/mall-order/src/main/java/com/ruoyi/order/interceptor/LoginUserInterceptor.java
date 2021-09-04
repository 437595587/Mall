package com.ruoyi.order.interceptor;

import com.ruoyi.mall.api.vo.Member;
import com.ruoyi.order.constant.AuthConstant;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<Member> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/order/*/status", requestURI);
        boolean math2 = antPathMatcher.match("/payed/notify", requestURI);
        if (match || math2) {
            return true;
        }
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(AuthConstant.LOGIN_USER);
        if (member != null) {
            loginUser.set(member);
            return true;
        } else {
            session.setAttribute("msg", "请先进行登录");
            response.sendRedirect("http://auth.mall.com/login.html");
            return false;
        }
    }
}
