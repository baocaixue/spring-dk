package com.isaac.ch3;

import com.isaac.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyPull {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/app-context.xml");

        MessageRenderer messageRenderer = context.getBean("renderer", MessageRenderer.class);
        messageRenderer.render();
    }
}
