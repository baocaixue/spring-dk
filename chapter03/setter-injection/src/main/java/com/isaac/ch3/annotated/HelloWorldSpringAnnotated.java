package com.isaac.ch3.annotated;

import com.isaac.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringAnnotated {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }
}
