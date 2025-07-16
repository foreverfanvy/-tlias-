package com.yaojingxi.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter("/*")// 拦截所有请求
@Slf4j
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DemoFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 拦截到请求时执行的逻辑，可以执行多次
        log.info("拦截到请求");
        //需要放行步骤
        chain.doFilter(request, response); // 放行请求，继续执行后续的过滤器或目标资源
    }

    @Override
    public void destroy() {
        log.info("DemoFilter destroy");
    }
}
