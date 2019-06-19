package com.isaac.ch4;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.support.GenericXmlApplicationContext;

public class NamedSingerDemo implements BeanNameAware {
    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    /**
     * 通过调用ApplicationContext.getBean()将Bean的第一个实例返回给应用程序之前调用BeanNameAware.setBeanName()，
     * 因此不需要检查Bean名称是否在sing()方法中可用
     */
    public void sing() {
        System.out.println("Singer " + name + " - sing()");
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring/app-context-xml.xml");
        ctx.refresh();

        NamedSingerDemo bean = ctx.getBean("johnMayer", NamedSingerDemo.class);
        bean.sing();

        ctx.close();
    }
}
