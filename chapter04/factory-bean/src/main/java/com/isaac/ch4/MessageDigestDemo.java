package com.isaac.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;


public class MessageDigestDemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring/app-context-xml.xml");
        ctx.refresh();

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("123456");

        ctx.close();
    }
}
