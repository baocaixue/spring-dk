package com.isaac.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Spring中定义的InitializingBean接口允许在bean代码中定义希望bean接收的Spring已经完成配置的通知。与使用初始化方法的方式相同
 */
public class SingerWithInterface implements InitializingBean {
    private static final String DEFAULT_NAME = "Taylor Swift";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "\tName: " + name + "\n\tAge: " + age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of any beans of type " + SingerWithInterface.class);
        }
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        getBean1("singerOne", ctx);
        getBean1("singerTwo", ctx);
        getBean1("singerThree", ctx);
        ctx.close();
    }

    public static SingerWithInterface getBean1(String beanName, ApplicationContext ctx) {
        try {
            SingerWithInterface bean = ctx.getBean(beanName, SingerWithInterface.class);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException e) {
            System.out.println("An error occured in bean configuration: " + e.getMessage());
            return null;
        }
    }
}
