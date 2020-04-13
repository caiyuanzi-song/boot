package com.cyz.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:cyz
 * @Date:2020/4/13 16:46
 * @Description:
 */
@Configuration
public class FanoutConfig {

    @Bean
    public Queue fanoutQueueA(){
        return new Queue("fanout.A",true);
    }

    @Bean
    public Queue fanoutQueueB(){
        return new Queue("fanout.B",true);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("TestFanoutExchange");
    }

    @Bean
    Binding bindingQueueA(){
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingQueueB(){
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }
}
