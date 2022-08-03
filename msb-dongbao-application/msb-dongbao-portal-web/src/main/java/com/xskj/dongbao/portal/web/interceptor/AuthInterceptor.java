package com.xskj.dongbao.portal.web.interceptor;

import com.xskj.dongbao.common.base.annotations.TokenCheck;
import com.xskj.dongbao.common.base.exception.TokenException;
import com.xskj.dongbao.common.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.tokens.TagTuple;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器进入");
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            throw new TokenException("token为空");
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(TokenCheck.class)){
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);
            if (annotation.required()){
                try {
                    JwtUtils.parseToken(token);
                } catch (Exception e) {
                    throw new TokenException("token错误");
                }
                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
