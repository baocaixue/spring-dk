package com.isaac.ch12;

import com.isaac.ch12.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class JmsHornetQDemo {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MessageSender messageSender = ctx.getBean(MessageSender.class);
        for (int i = 0; i < 10; i++) {
            messageSender.sendMessage((i + 1) + "");
        }
        System.in.read();
        ctx.close();
    }
}
