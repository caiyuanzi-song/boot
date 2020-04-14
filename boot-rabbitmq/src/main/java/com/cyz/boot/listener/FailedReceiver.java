package com.cyz.boot.listener;

import com.cyz.boot.bo.RetryData;
import com.cyz.boot.config.FailedConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author:cyz
 * @Date:2020/4/14 21:10
 * @Description:
 */
@Component
@Slf4j
public class FailedReceiver {

    @RabbitListener(queues = FailedConfig.FAILED_QUEUE)
    @RabbitHandler
    public void receiverDirect(Map data, Channel channel, Message message) throws Exception{
        try {
            log.info("FailedReceiver兼听到的消息: id=" + data.get("id") + " ;retry = " +data.get("retry"));
            log.info("人工处理");
        }catch (Exception e){
            log.info("receiver error");
        }
    }
}
