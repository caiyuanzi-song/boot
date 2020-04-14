package com.cyz.boot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:cyz
 * @Date:2020/4/14 15:12
 * @Description:
 */
@Configuration
public class WorkConfig {

//    处理业务的队列
    public final static String WORK_QUEUE = "retry.work.queue";

//    处理业务的交换器
    public final static String WORK_EXCHANGE = "retry.work.exchange";

//    处理业务的路由Key
    public final static String WORK_KEY = "retry.work.key";

    @Bean
    DirectExchange retryWorkExchange(){
        return new DirectExchange(WORK_EXCHANGE);
    }

    @Bean
    public Queue retryWorkQueue(){
        return QueueBuilder
                .durable(WORK_QUEUE)
                .build();
    }

    @Bean
    public Binding workRetryBinding(){
        return BindingBuilder
                .bind(retryWorkQueue())
                .to(retryWorkExchange())
                .with(WORK_KEY);
    }

}
