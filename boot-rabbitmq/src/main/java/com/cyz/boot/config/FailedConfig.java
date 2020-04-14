package com.cyz.boot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:cyz
 * @Date:2020/4/14 19:46
 * @Description:
 */
@Configuration
public class FailedConfig {

    /**
     * 处理业务的队列
     */
    public final static String FAILED_QUEUE = "retry.failed.queue";

    /**
     * 处理业务的交换器
     */
    public final static String FAILED_EXCHANGE = "retry.failed.exchange";

    /**
     * 处理业务的路由key
     */
    public final static String FAILED_KEY = "retry.failed.key";


    @Bean
    DirectExchange retryFailedExchange(){
        return new DirectExchange(FAILED_EXCHANGE);
    }

    @Bean
    public Queue retryFailedQueue(){
        return QueueBuilder
                .durable(FAILED_QUEUE)
                .build();
    }

    @Bean
    public Binding failedRetryBinding(){
        return BindingBuilder
                .bind(retryFailedQueue())
                .to(retryFailedExchange())
                .with(FAILED_KEY);
    }
}

