package com.baizhi.config;


import com.baizhi.intercepter.LoginIntercepter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //                          添加哪个拦截器                        //拦截那些请求
        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**")
                                                        //排除指定的请求;
                                                       .excludePathPatterns("/admin/*");
    }
}
