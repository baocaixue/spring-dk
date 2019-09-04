package com.isaac.ch18.config;

import com.isaac.ch18.web.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.isaac.ch18")
public class ServerConfig {
    @Bean
    public Server server() {
        return new Server();
    }
}
