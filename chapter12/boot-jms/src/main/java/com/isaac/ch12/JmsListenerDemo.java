package com.isaac.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class JmsListenerDemo {
    private static final Logger logger = LoggerFactory.getLogger(JmsListenerConfig.class);

    @JmsListener(destination = "isaac", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            logger.info(">>> Received: " + textMessage.getText());
        } catch (JMSException e) {
            logger.error("JMS Error: " , e);
        }
    }
}
