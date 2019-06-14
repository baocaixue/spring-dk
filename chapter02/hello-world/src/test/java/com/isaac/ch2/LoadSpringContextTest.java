package com.isaac.ch2;

import com.isaac.ch2.annotated.HelloWorldConfiguration;
import com.isaac.ch2.decoupled.MessageRenderer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoadSpringContextTest {
    @Test public void testLoadContextByXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/app-context.xml");
        MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
        Assert.assertNotNull(renderer);
    }

    @Test public void testLoadContextByAnnotated() {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
        Assert.assertNotNull(renderer);
    }
}
