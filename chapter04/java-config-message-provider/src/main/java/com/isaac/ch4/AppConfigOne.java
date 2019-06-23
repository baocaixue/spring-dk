package com.isaac.ch4;

import com.isaac.ch2.decoupled.MessageProvider;
import com.isaac.ch2.decoupled.MessageRenderer;
import com.isaac.ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:message.properties")
public class AppConfigOne {
    private final Environment env;

    //@Autowired
    public AppConfigOne(Environment env) {
        this.env = env;
    }

    @Bean
    @Lazy
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider(env.getProperty("message"));
    }

    @Bean
    @Scope(value = "prototype")
    @DependsOn(value = "messageProvider")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());
        return renderer;
    }
}
