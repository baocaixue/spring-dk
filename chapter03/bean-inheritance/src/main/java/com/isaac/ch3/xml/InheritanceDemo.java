package com.isaac.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InheritanceDemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        Singer parent = ctx.getBean("parent", Singer.class);
        Singer child = ctx.getBean("child", Singer.class);

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);
    }
}
