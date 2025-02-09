package com.lyc.lease.web.admin.custom.interceptor;

import com.lyc.lease.common.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ClassName AuthenticationInterceptor
 * @Description TODO 登录状态校验拦截器
 * @Author LuKey_C
 * @Date 2025/2/9 15:50
 * @Version 1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
        JwtUtil.parseToken(token);
        return true;
    }

}
