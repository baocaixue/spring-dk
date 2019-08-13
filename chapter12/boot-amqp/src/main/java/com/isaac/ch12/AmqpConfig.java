package com.isaac.ch12;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 相较于Spring的配置，SpringBoot做了简化。不需要定义RabbitTemplate、RabbitAdmin和SimpleRabbitListenerContainerFactory bean。
 */
@Configuration
public class AmqpConfig {
    private final static String queueName = "forecasts";
    private final static String exchangeName = "weather";

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("127.0.0.1");
    }

    @Bean
    public Queue forecasts() {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange weather() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Binding dataBinding(DirectExchange directExchange, Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(queueName);
    }

}
