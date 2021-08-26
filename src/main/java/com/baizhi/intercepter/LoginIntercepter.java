package com.baizhi.intercepter;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginIntercepter implements HandlerInterceptor {

    private StringRedisTemplate redisTemplate;
    @Override//预先经过的方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getParameter("token");
//        System.out.println("拦截器执行了  "+token);
//        if(redisTemplate.hasKey(token)){
//            redisTemplate.expire(token, 30, TimeUnit.MINUTES);
            return true;//放行
//        }else {
//            throw new RuntimeException();
//        }

    }

    @Override  //controller执行之后回到postHandle执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override   //在响应完全结束后会进入的方法
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
