package com.cyz.boot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:cyz
 * @Date:2020/4/14 15:56
 * @Description:
 */
@Configuration
public class RetryConfig {

//    重试的队列
    public final static String RETRY_QUEUE = "retry.queue";

//    重试的交换器
    public final static String RETRY_EXCHANGE = "retry.exchange";

//    处理业务的路由key
    public final static String RETRY_KEY = "retry.key";

//    超时时间
    private static final int QUEUE_EXPIRATION = 4000;

    @Bean
    DirectExchange retryExchange(){
        return new DirectExchange(RETRY_EXCHANGE);
    }

    @Bean
    public Queue retryQueue(){
        return QueueBuilder.durable(RETRY_QUEUE)
                .withArgument("x-dead-letter-exchange",WorkConfig.WORK_EXCHANGE)
                .withArgument("x-dead-letter-routing-key",WorkConfig.WORK_KEY)
                .withArgument("x-message-ttl",QUEUE_EXPIRATION)
                .build();
    }

    @Bean
    public Binding retryBinding(){
        return BindingBuilder
                .bind(retryQueue())
                .to(retryExchange())
                .with(RETRY_KEY);
    }
}
