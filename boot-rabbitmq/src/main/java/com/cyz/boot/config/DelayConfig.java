package com.cyz.boot.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author:cyz
 * @Date:2020/4/14 10:32
 * @Description:
 */
@Configuration
public class DelayConfig {

    public final static String DELAY_QUEUE_PER_QUEUE_TTL_NAME = "delay_queue_per_queue_ttl";
    public final static String DELAY_PROCESS_QUEUE_NAME = "delay_process_queue";
    public final static String DELAY_EXCHANGE_NAME = "delay_exchange";

    public final static int QUEUE_EXPIRATION = 60 * 1000;

    /**
     * x-dead-letter-exchange : DLX
     * x-dead-letter-routing-key : dead letter 携带的routing key
     * x-message-ttl 设置队列的过期时间
     * @return
     */
    @Bean
    Queue delayQueueTTL(){
        return QueueBuilder.durable(DELAY_QUEUE_PER_QUEUE_TTL_NAME)
                .withArgument("x-dead-letter-exchange",DELAY_EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key",DELAY_PROCESS_QUEUE_NAME)
//                .withArgument("x-message-ttl",QUEUE_EXPIRATION)
                .build();
    }

    @Bean
    Queue delayProcessQueue(){
        return QueueBuilder.durable(DELAY_PROCESS_QUEUE_NAME).build();
    }

    @Bean
    DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    @Bean
    Binding dlxBinding(Queue delayProcessQueue,DirectExchange delayExchange){
        return BindingBuilder.bind(delayProcessQueue)
                .to(delayExchange())
                .with(DELAY_PROCESS_QUEUE_NAME);
    }
}
