package com.yaojingxi.filter;

import com.yaojingxi.utils.CurrentHolder;
import com.yaojingxi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
@WebFilter("/*") // 拦截所有请求
public class TokenFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;// 将ServletRequest转换为HttpServletRequest，以便获取请求头等信息
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;// 将ServletResponse转换为HttpServletResponse，以便设置响应状态码等信息

        //1.获取请求路径
        String uri = httpServletRequest.getRequestURI();
        //2.判断请求路径是否是登录请求
        // 假设登录请求的路径是 "/login"，可以根据实际情况修改
        if (uri.contains("/login")) {
            //3.如果是登录请求，直接放行
            log.info("登录操作放行，路径：{}", uri);
            chain.doFilter(request, response); // 放行请求，继续执行后续的过滤器或目标资源
            return;
        }
        //4.如果不是登录请求，获取请求头中的token信息
        String token = httpServletRequest.getHeader("token");
        //5.判断token是否不存在，如果不存在，返回401状态码
        if (token == null || token.isEmpty()) {
            log.warn("请求未携带token，返回401状态码");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置响应状态码为401
            return; // 终止请求处理
        }
        //6.如果token存在，验证token的合法性
        // 这里可以调用JWT工具类或其他验证逻辑来验证token的合法性
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer id = (Integer) claims.get("id");
            CurrentHolder.setCurrentId(id);
            log.info("当前登录的用户is={}", id);

        } catch (Exception e) {
            log.error("Token验证失败，返回401状态码", e);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置响应状态码为401
            return; // 终止请求处理
        }

        //7.如果token合法，放行请求，继续执行后续的过滤器或目标资源
        log.info("令牌合法放行");
        chain.doFilter(request, response);

        //8.在请求处理完成后，清除当前线程中的用户ID
        CurrentHolder.remove();
    }


}
