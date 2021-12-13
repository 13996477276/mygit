package com.example.thymeleaf.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过session判断是否登录成功
        HttpSession session = request.getSession();
        if (request.getSession().getAttribute("LoginUser") != null) {
            return true;
        } else {
            request.setAttribute("msg", "For security,please login first");
            //debug  System.out.println("-------------------------------------");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
    }
}
