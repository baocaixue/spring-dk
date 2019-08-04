package com.isaac.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com.isaac.ch12")
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
        jmsTemplate.setDeliveryDelay(5000L);
        for (int i = 0; i < 10; i++) {
            int num = i + 1;
            logger.info(">>> sending: " + num);
            jmsTemplate.convertAndSend("isaac", "test message: " + num);
        }

        System.in.read();
        ctx.close();
    }

}
