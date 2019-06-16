package com.isaac.ch3.annotated;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotatedDependsOnDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-02.xml");
        ctx.refresh();

        Singer johnMayer = ctx.getBean("johnMayer", Singer.class);
        johnMayer.sing();

        ctx.close();
    }
}
