package com.isaac.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;

public class SingerWithJSR250 {

    private static final String DEFAULT_NAME = "Taylor Swift";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 使用init-method或@PostConstruct配置初始化时，声明私有访问权限的方法禁止外部调用，Spring IOC可以通过反射来调用它
     */
    @PostConstruct
    private void init() {
        System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of any beans of type " + SingerWithJSR250.class);
        }
    }

    public String toString() {
        return "\tName: " + name + "\n\tAge: " + age;
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();

        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);

        ctx.close();
    }

    public static SingerWithJSR250 getBean(String beanName, ApplicationContext ctx) {
        try {
            SingerWithJSR250 bean = ctx.getBean(beanName, SingerWithJSR250.class);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException e) {
            System.out.println("An error occured in bean configuration: " + e.getMessage());
            return null;
        }
    }

}
