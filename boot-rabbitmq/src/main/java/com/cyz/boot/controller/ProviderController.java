package com.cyz.boot.controller;

import com.cyz.boot.bo.RetryData;
import com.cyz.boot.config.DelayConfig;
import com.cyz.boot.config.WorkConfig;
import com.cyz.boot.util.ExpirationMessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author:cyz
 * @Date:2020/4/13 10:02
 * @Description:
 */
@RestController
public class ProviderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/sendDirectMessage")
    public String sendDirectMessage(@RequestBody String msg){
        String msgId = String.valueOf(UUID.randomUUID());
        String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap<>();
        map.put("msgId",msgId);
        map.put("sendTime",sendTime);
        map.put("msg",msg);
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",map);
        return "ok";
    }

    @PostMapping("/sendFanoutMessage")
    public String sendFanoutMessage(@RequestBody String msg){
        String msgId = String.valueOf(UUID.randomUUID());
        String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap<>();
        map.put("msgId",msgId);
        map.put("sendTime",sendTime);
        map.put("msg",msg);
        rabbitTemplate.convertAndSend("fanoutExchange",null,map);
        return "ok";
    }

    @RequestMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
        return "ok";
    }

    @RequestMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return "ok";
    }

    @RequestMapping("/sendDeadMessage")
    public String sendDeadMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", "test dead letter queue");
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("TestDeadExchange", "TestDeadRouting", womanMap);
        return "ok";
    }

    /**
     * x-message-ttl 设置队列的过期时间
     * @return
     */
    @RequestMapping("/sendDelayMessage")
    public String sendDelayMessage(){
        rabbitTemplate.convertAndSend(DelayConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME,"Message from delay_queue_per_queue_ttl with expiration "+DelayConfig.QUEUE_EXPIRATION);
        return "ok";
    }

    /**
     * 针对每条消息不同的过期时间的情况
     * @return
     */
    @RequestMapping("/sendDelayMessageEx")
    public String sendDelayMessageEx(){
        for (int i=0;i<4;i++){
            long expiration = i * 5000;
            System.out.println(i + ":" + new Date().toString());
            rabbitTemplate.convertAndSend(DelayConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME,
                    (Object) ("Message from delay_queue_per_queue_ttl with expiration "+expiration),
                    new ExpirationMessagePostProcessor(expiration + ""));
        }
        return "ok";
    }

    /**
     * 消息重试
     * @return
     */
    @RequestMapping("/sendRetryMessage")
    public String sendRetryMessage(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("retry",1);
        rabbitTemplate.convertAndSend(
                WorkConfig.WORK_EXCHANGE,
                WorkConfig.WORK_KEY,
                map
        );
        return "ok";
    }

}
