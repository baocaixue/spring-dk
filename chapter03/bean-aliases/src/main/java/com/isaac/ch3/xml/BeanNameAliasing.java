package com.isaac.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

public class BeanNameAliasing {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-02.xml");
        ctx.refresh();

        String s1 = ctx.getBean("john", String.class);
        String s2 = ctx.getBean("jon", String.class);
        String s3= ctx.getBean("johnny", String.class);
        String s4 = ctx.getBean("jonathan", String.class);
        String s5 = ctx.getBean("jim", String.class);
        String s6 = ctx.getBean("ion", String.class);

        assert s1 == s2;
        assert s2 == s3;
        assert s3 == s4;
        assert s4 == s5;
        assert s5 == s6;

        Map<String, String> beans = ctx.getBeansOfType(String.class);
        if (beans.size() == 1)
            System.out.println("There is only one String bean.");

        ctx.close();
    }
}
