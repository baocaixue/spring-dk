package com.isaac.ch3.annotated;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class AnnotatedBeanNaming {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotated.xml");
        ctx.refresh();

        Map<String, Singer> singers = ctx.getBeansOfType(Singer.class);
        singers.forEach((key, value) -> System.out.println("id: " + key + "\t aliases: " + Arrays.toString(ctx.getAliases(key))));

        ctx.close();
    }
}
