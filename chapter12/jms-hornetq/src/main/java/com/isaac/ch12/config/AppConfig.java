package com.isaac.ch12.config;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.hornetq.jms.client.HornetQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJms
@ComponentScan(basePackages = "com.isaac.ch12")
public class AppConfig {
    @Bean
    public HornetQQueue isaac() {
        //isaac queue
        return new HornetQQueue("isaac");
    }

    /**
     * ConnectionFactory接口由HornetQ Java库（HornetQJMSConnectionFactory类）提供，用于创建与JMS提供程序的连接
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        Map<String, Object> connDetails = new HashMap<>();
        connDetails.put(TransportConstants.HOST_PROP_NAME, "127.0.0.1");
        connDetails.put(TransportConstants.PORT_PROP_NAME, "5445");
        TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), connDetails);
        return new HornetQJMSConnectionFactory(false, transportConfiguration);
    }

    /**
     * JmsListenerContainerFactory 会创建使用普通JMS客户端API接收JMS消息的消息监听器容器
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("3-5");
        return factory;
    }

    /**
     * 用于将JMS消息发送到isaac队列
     * @return
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestination(isaac());
        return jmsTemplate;
    }
}
