package com.isaac.ch3;

import com.isaac.ch2.decoupled.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class LazyConfigBeans {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-lazy-xml.xml");
        ctx.refresh();
        MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
        renderer.render();
        ctx.close();
    }
}
