package com.isaac.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class MessageSourceDemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        Locale english = Locale.ENGLISH;
        Locale china = Locale.CHINA;

        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, china));

        System.out.println(ctx.getMessage("nameMsg", new String[]{"isaac", "bao"}, english));
        System.out.println(ctx.getMessage("nameMsg", new String[]{"包", "才学"}, china));

        ctx.close();
    }
}
