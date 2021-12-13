package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    //处理登录请求
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        System.out.println("Debug++" + username + password);
        if ("admin".equals(username) && "123456".equals(password)) {
            session.setAttribute("LoginUser", username);
            //转发重定向解决url的显示问题
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "UserName or Password error");
            return "index";
        }

    }

    @RequestMapping("/user/loginout")
    public String logout(HttpSession session) {
        session.invalidate();
        // model.addAttribute("msg", "Welcome To Login nextTime");
        return "redirect:/index.html";
    }

}
