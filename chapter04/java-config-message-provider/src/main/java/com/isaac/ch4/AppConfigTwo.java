package com.isaac.ch4;

import com.isaac.ch2.decoupled.MessageProvider;
import com.isaac.ch2.decoupled.MessageRenderer;
import com.isaac.ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.isaac.ch4.annotated"})
public class AppConfigTwo {
    @Autowired
    private MessageProvider messageProvider;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider);
        return renderer;
    }
}
