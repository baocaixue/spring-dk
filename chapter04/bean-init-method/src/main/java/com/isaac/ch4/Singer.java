package com.isaac.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 接收初始化回调的一种方法是在bean上指定一个方法作为初始化方法，并告诉Spring将此方法用作初始化方法（这里是init方法）。
 * <p>
 * 当只有几个同类型的bean，或希望应用程序与Spring分离时很有用。
 * <p>
 * 使用这种机制的另一个原因是，为了能够让Spring应用程序使用先前构建的或由第三方供应商提供的bean。
 */
public class Singer {
    private static final String DEFAULT_NAME = "Taylor Swift";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //初始化方法
    public void init() {
        System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of any beans of type " + Singer.class);
        }
    }

    public String toString() {
        return "\tName: " + name + "\n\tAge: " + age;
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);

        ctx.close();
    }

    public static Singer getBean(String beanName, ApplicationContext ctx) {
        try {
            Singer bean = ctx.getBean(beanName, Singer.class);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException e) {
            System.out.println("An error occured in bean configuration: " + e.getMessage());
            return null;
        }
    }
}
