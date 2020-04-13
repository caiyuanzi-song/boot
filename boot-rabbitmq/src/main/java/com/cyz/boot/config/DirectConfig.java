package com.cyz.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author:cyz
 * @Date:2020/4/13 9:58
 * @Description:
 */
@Configuration
public class DirectConfig {

    @Bean
    public Queue testDirectQueue(){
        return new Queue("TestDirectQueue",true);
    }

    @Bean
    DirectExchange testDirectExchange(){
        return new DirectExchange("TestDirectExchange");
    }

    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("TestDirectRouting");
    }
}
