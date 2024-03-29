package com.wen.springbootsqldata.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
    // 后台监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //注意，这儿的星号只能是一个
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台需要有人登陆，账号密码配置
        HashMap<String,String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","admin");//登陆的key是固定的loginUsername loginPassword
        initParameters.put("loginPassword","123456");
        initParameters.put("allow","");//允许谁访问
        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }
}
