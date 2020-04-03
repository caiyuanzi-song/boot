package com.cyz.boot.config;

import com.cyz.boot.filter.TraceFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
/**
 * @Author:cyz
 * @Date:2020/4/3 16:23
 * @Description:
 */
@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean webTraceFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TraceFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
