package com.cyz.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:cyz
 * @Date:2020/4/13 16:23
 * @Description:
 */
@Configuration
public class ConsumerConfig {

    @Bean
    public Queue TestDirectQueue(){
        return new Queue("TestDirectQueue",true);
    }
}
