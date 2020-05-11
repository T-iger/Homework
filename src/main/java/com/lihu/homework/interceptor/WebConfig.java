package com.lihu.homework.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Li
 **/
@Configuration   //配置类
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/login/**")   //过滤/**请求路径
                .addPathPatterns("/")
                .excludePathPatterns("/loginpost")  //去除这个路径
                .excludePathPatterns("/login")
                .excludePathPatterns("/login/zhuce");

    }
}
