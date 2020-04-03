package com.cyz.boot.config;

import com.cyz.boot.interceptor.TraceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:cyz
 * @Date:2020/4/3 15:34
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceInterceptor());
    }

    @Bean
    public TraceInterceptor traceInterceptor(){
        return new TraceInterceptor();
    }
}
