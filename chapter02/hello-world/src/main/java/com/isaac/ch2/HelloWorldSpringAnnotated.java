package com.isaac.ch2;

import com.isaac.ch2.annotated.HelloWorldConfiguration;
import com.isaac.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringAnnotated {
    public static void main(String[] args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        MessageRenderer renderer = applicationContext.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }
}
