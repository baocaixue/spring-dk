package com.isaac.ch3;

import com.isaac.ch3.annotated.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class NonSingletonDemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotated.xml");
        ctx.refresh();

        Singer singer1 = ctx.getBean("nonSingleton", Singer.class);
        Singer singer2 =  ctx.getBean("nonSingleton", Singer.class);

        System.out.println("Identity Equal?: " + (singer1 ==singer2));
        System.out.println("Value Equal:? " + singer1.equals(singer2));
        System.out.println(singer1);
        System.out.println(singer2);

        ctx.close();
    }
}
