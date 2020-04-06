package com.cyz.boot.config;

import com.baidu.unbiz.fluentvalidator.ValidateCallback;
import com.baidu.unbiz.fluentvalidator.interceptor.FluentValidateInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:cyz
 * @Date:2020/4/6 17:20
 * @Description:
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    public FluentValidateInterceptor fluentValidateInterceptor(){
        FluentValidateInterceptor fluentValidateInterceptor = new FluentValidateInterceptor();
        fluentValidateInterceptor.setCallback(validateCallback());
        return fluentValidateInterceptor;
    }

    @Bean
    public ValidateCallback validateCallback(){
        return  new HussarValidateCallBack();
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
        proxyCreator.setBeanNames("*controller");
        proxyCreator.setInterceptorNames("fluentValidateInterceptor");
        return proxyCreator;
    }

}
