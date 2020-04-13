package com.cyz.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:cyz
 * @Date:2020/4/13 19:01
 * @Description:
 */
@Configuration
public class DeadConfig {

    @Bean
    public Queue testDirectQueue(){
        Map<String,Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange","DeadExchange");
        params.put("x-dead-letter-routing-key","DeadRouting");
        return new Queue("TestDeadQueue",true,false,false,params);
    }

    @Bean
    public Queue TestDeadQueue(){
        return new Queue("DeadQueue",true);
    }
}
