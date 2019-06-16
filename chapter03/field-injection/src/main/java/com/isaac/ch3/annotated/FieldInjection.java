package com.isaac.ch3.annotated;

import org.springframework.context.support.GenericXmlApplicationContext;

public class FieldInjection {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context.xml");
        ctx.refresh();

        Singer singer = ctx.getBean("singer", Singer.class);
        singer.sing();
        
        ctx.close();
    }
}
