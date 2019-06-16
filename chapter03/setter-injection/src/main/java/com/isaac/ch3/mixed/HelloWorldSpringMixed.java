package com.isaac.ch3.mixed;

import com.isaac.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringMixed {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext
                (HelloWorldConfiguration.class);
        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}
