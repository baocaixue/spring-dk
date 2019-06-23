package com.isaac.ch4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        assert ctx != null;
        logger.info("the beans you were looking for:");

        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);

        HelloWorld bean = ctx.getBean(HelloWorld.class);
        bean.sayHello();

        System.in.read();//控制台有输入前阻塞
        ctx.close();
    }
}
