package com.isaac.ch4;

import com.isaac.ch2.decoupled.MessageRenderer;
import com.isaac.ch4.mixed.AppConfigFive;
import com.isaac.ch4.multiple.AppConfigThree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigExampleTwo {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigFive.class);//AppConfigTwo.class AppConfigThree
        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
