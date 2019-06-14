package com.isaac.ch2;

import com.isaac.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpringDI {
    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/app-context.xml");
        MessageRenderer renderer = applicationContext.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }
}
