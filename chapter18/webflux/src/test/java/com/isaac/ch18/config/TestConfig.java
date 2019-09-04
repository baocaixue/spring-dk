package com.isaac.ch18.config;

import com.isaac.ch18.web.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@Configuration
@Import(ServerConfig.class)
public class TestConfig {
    @Autowired
    private Server server;

    @Bean
    public WebTestClient testClient() {
        return WebTestClient.bindToRouterFunction(server.routerFunction())
                .configureClient()
                .baseUrl("http://localhost:8080/singers")
                .build();
    }
}
