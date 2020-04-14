package com.cyz.boot.util;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @Author:cyz
 * @Date:2020/4/14 11:12
 * @Description:
 */

public class ExpirationMessagePostProcessor implements MessagePostProcessor {

    private final String ttl;

    public ExpirationMessagePostProcessor(String ttl) {
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setExpiration(ttl);
        return message;
    }
}
