package com.isaac.ch4.mixed;

import com.isaac.ch2.decoupled.MessageProvider;
import com.isaac.ch4.ConfigurableMessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigSix {
    @Bean
    public MessageProvider provider() {
        return new ConfigurableMessageProvider("Love on the weekend");
    }
}
