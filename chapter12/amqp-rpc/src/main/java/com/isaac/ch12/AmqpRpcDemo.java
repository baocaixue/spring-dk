package com.isaac.ch12;

import com.isaac.ch12.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AmqpRpcDemo {
    private static final Logger logger = LoggerFactory.getLogger(AmqpRpcDemo.class);

    public static void main(String[] args){
        //xmlTest();
        annotationTest();
    }

    private static void annotationTest() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("FL");
        rabbitTemplate.convertAndSend("MA");
        rabbitTemplate.convertAndSend("CA");
        ctx.close();
    }

    private static void xmlTest() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/amqp-rpc-app-context.xml");
        ctx.refresh();

        WeatherService weatherService = ctx.getBean(WeatherService.class);
        logger.info("Forecast for FL: " + weatherService.getForecast("FL"));
        logger.info("Forecast for MA: " + weatherService.getForecast("MA"));
        logger.info("Forecast for CA: " + weatherService.getForecast("CA"));

        ctx.close();
    }
}
