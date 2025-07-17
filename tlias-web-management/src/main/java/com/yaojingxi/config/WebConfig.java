package com.yaojingxi.config;

import com.yaojingxi.interceptor.DemoInterceptor;
import com.yaojingxi.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
    //    @Autowired
    //    private DemoInterceptor demoInterceptor; // 注入DemoInterceptor
    //@Autowired
    //private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         //添加拦截器
    //         registry.addInterceptor(tokenInterceptor)
    //                 .addPathPatterns("/**") // 拦截所有请求
    //                 .excludePathPatterns("/login"); // 排除登录请求

        // 其他拦截器配置可以在这里添加
        // registry.addInterceptor(new AnotherInterceptor())
        //         .addPathPatterns("/api/**"); // 只拦截/api/路径下的请求
    }

}
