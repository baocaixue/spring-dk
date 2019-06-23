package com.isaac.ch4;

import com.isaac.ch3.xml.Singer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class GroovyBeansFromJava {
    public static void main(String[] args){
        ApplicationContext ctx = new GenericGroovyApplicationContext("classpath:beans.groovy");
        Singer singer = ctx.getBean("singer", Singer.class);
        System.out.println(singer);
    }
}
