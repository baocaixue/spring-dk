package com.isaac.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * JMS监听：消息接收
 *
 * Spring4.1之前需要创建一个实现了javax.jms.MessageListener接口并实现onMessage()方法的类。
 * Spring4.1中添加了@JmsListener注解。该注解用于bean方法，从而将它们标记为处于指定目的地（队列或主题）的JMS消息监听器目标
 */
@Component("messageListener")
public class SimpleMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageListener.class);

    /*JMSListener注解需要使用@EnableJms或XML元素声明（<jms:annotation-driven/>）来处理*/
    @JmsListener(destination = "isaac", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            logger.info(">>> Received: " + textMessage.getText());
        } catch (JMSException ex) {
            logger.error("JMS Error " + ex);
        }
    }
}
