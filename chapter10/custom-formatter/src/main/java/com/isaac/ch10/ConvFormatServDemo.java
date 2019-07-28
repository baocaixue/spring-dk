package com.isaac.ch10;

import com.isaac.ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServDemo {
    private static Logger logger = LoggerFactory.getLogger(ConvFormatServDemo.class);

    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Singer taylor = ctx.getBean("taylor", Singer.class);
        logger.info("Singer info : " + taylor);

        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        //Formatting datetime
        logger.info("Birthday of singer is : " + conversionService.convert(taylor.getBirthDate(), String.class));

        ctx.close();
    }
}
