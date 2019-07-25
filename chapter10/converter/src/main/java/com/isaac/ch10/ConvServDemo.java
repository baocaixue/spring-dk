package com.isaac.ch10;

import com.isaac.ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ConvServDemo {
    private static Logger logger = LoggerFactory.getLogger(ConvServDemo.class);

    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Singer singer = ctx.getBean(Singer.class);
        logger.info("Singer info : " + singer);
        ctx.close();
    }
}
