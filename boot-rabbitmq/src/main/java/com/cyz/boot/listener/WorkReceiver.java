package com.cyz.boot.listener;

import com.cyz.boot.bo.RetryData;
import com.cyz.boot.config.FailedConfig;
import com.cyz.boot.config.RetryConfig;
import com.cyz.boot.config.WorkConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @Author:cyz
 * @Date:2020/4/14 19:59
 * @Description:
 */
@Component
@Slf4j
public class WorkReceiver {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = WorkConfig.WORK_QUEUE)
    @RabbitHandler
    public void receiveDirect(Map data, Channel channel, Message message){
        try {
            Integer retry = (Integer) data.get("retry");
            Integer id = (Integer) data.get("id");
            log.info("WorkReceiver兼听到消息：id = " + id +";retry = " + retry);
            log.info("重试次数：{}",retry);
            if (retry < 3 || id == 1){
                data.put("retry",retry + 1);
                throw new RuntimeException("进入重试");
            }
            log.info("消费成功！");
        }catch (Exception e){
            log.info("开始重试");
            Integer retry = (Integer) data.get("retry");
            if (retry > 3){
                rabbitTemplate.convertAndSend(
                        FailedConfig.FAILED_EXCHANGE,
                        FailedConfig.FAILED_KEY,
                        data
                );
                log.info("receiver failed");
            }else {
                rabbitTemplate.convertAndSend(
                        RetryConfig.RETRY_EXCHANGE,
                        RetryConfig.RETRY_KEY,
                        data
                );
                log.info("receiver error");
            }
        }
    }
}
