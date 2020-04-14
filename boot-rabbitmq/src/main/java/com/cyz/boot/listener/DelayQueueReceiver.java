package com.cyz.boot.listener;

import com.cyz.boot.config.DelayConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author:cyz
 * @Date:2020/4/14 11:03
 * @Description:
 */
@Component
public class DelayQueueReceiver {

    @RabbitListener(queues = DelayConfig.DELAY_PROCESS_QUEUE_NAME)
    @RabbitHandler
    public void process(String msg){
        System.out.println(new Date().toString() + ":" + msg);
    }
}
