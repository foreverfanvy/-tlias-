package com.yaojingxi.interceptor;

import com.yaojingxi.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //1.获取请求路径
        String uri = request.getRequestURI();
        //2.判断请求路径是否是登录请求
        // 假设登录请求的路径是 "/login"，可以根据实际情况修改
        if (uri.contains("/login")) {
            //3.如果是登录请求，直接放行
            log.info("登录操作放行，路径：{}", uri);
            return true;
        }
        //4.如果不是登录请求，获取请求头中的token信息
        String token = request.getHeader("token");
        //5.判断token是否不存在，如果不存在，返回401状态码
        if (token == null || token.isEmpty()) {
            log.warn("请求未携带token，返回401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置响应状态码为401
            return false; // 终止请求处理
        }
        //6.如果token存在，验证token的合法性
        // 这里可以调用JWT工具类或其他验证逻辑来验证token的合法性
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.error("Token验证失败，返回401状态码", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置响应状态码为401
            return false; // 终止请求处理
        }

        //7.如果token合法，放行请求，继续执行后续的过滤器或目标资源
        log.info("令牌合法放行");
        return  true;
    }

}
