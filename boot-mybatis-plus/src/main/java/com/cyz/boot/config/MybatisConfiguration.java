package com.cyz.boot.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:cyz
 * @Date:2020/4/6 16:23
 * @Description:
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    @ConditionalOnClass(BaseMapper.class)
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
