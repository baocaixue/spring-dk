package com.isaac.ch11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@ComponentScan(basePackages = "com.isaac.ch11")
public class AppConfig {
    @Bean
    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor();
        return new SyncTaskExecutor();
    }
}
