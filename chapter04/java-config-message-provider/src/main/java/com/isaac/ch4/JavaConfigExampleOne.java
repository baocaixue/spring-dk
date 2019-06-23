package com.isaac.ch4;

import com.isaac.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigExampleOne {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigOne.class);
        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
