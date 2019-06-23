package com.isaac.ch4;

import org.springframework.context.ApplicationListener;

public class MessageEventListener implements ApplicationListener<MessageEvent>{
    @Override
    public void onApplicationEvent(MessageEvent event) {
        System.out.println("Message Received: " + event.getMsg());
    }
}
