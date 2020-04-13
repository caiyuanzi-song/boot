package com.cyz.boot.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Author:cyz
 * @Date:2020/4/13 16:25
 * @Description:
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectListener {

    @RabbitHandler
    public void process(Map msg){
        System.out.println("DirectListener收到的消息是 ："+msg.toString());
    }

    @RabbitHandler
    public void process(Map obj, Channel channel, Message message) throws IOException{
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("DirectConsumer收到的消息并ACK返回： "+obj.toString());
        }catch (Exception e){
            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }
}
