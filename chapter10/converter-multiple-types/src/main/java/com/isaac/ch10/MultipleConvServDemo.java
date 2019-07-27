package com.isaac.ch10;

import com.isaac.ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.*;

public class MultipleConvServDemo {
    private static Logger logger = LoggerFactory.getLogger(MultipleConvServDemo.class);

    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Singer taylor = ctx.getBean("taylor", Singer.class);
        logger.info("Singer info : " + taylor);

        ConversionService conversionService = ctx.getBean(ConversionService.class);
        AnotherSinger anotherSinger = conversionService.convert(taylor, AnotherSinger.class);
        logger.info("Another Singer info : " + anotherSinger);

        /*转换服务所支持的其他类型*/
        //字符串->字符串数组
        String[] stringArray = conversionService.convert("hello,world,!", String[].class);
        logger.info("String Array : " + Arrays.toString(stringArray));

        //ArrayList->HashSet
        List<String> listString = new ArrayList<>();
        listString.add("Isaac");
        listString.add("Isaac");
        listString.add("Taylor");
        HashSet<String> setString = conversionService.convert(listString, HashSet.class);
        assert setString != null;
        setString.forEach(logger::info);
    }
}
