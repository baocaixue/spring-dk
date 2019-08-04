package com.isaac.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * 消息发送
 */
@Component("messageSender")
public class SimpleMessageSender implements MessageSender{
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String message) {
        jmsTemplate.setDeliveryDelay(5000L);

        jmsTemplate.send(session -> {
            TextMessage jmsMsg = session.createTextMessage(message);
            logger.info(">>> Sending: " + message);
            return jmsMsg;
        });
    }
}
