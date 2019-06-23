package com.isaac.ch4;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Publisher implements ApplicationContextAware{
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void publish(String msg){
        applicationContext.publishEvent(new MessageEvent(this, msg));
    }

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/app-context-xml.xml");
        Publisher publisher = ctx.getBean("publisher", Publisher.class);
        publisher.publish("I send an SOS to the world!");
        publisher.publish("... anybody received ...");
        publisher.publish("... ... ...");
    }
}
