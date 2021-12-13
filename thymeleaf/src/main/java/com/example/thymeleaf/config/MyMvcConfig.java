package com.example.thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/index.html", "/", "/user/login", "/css/**", "/js/**", "/img/**");
    }
    //视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //和注册servlet的requestmapping效果一样
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //main解决了url显示问题但是要写拦截器
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //中英传参转换
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


}
