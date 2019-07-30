package com.isaac.ch11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@Import(DataServiceConfig.class)
@EnableScheduling//要为任务调度启用注解支持，需要在XML中使用<task:annotation-driven>标记，或使用@EnableScheduling。启用后Spring可以检测@Scheduled注解
public class AppConfig {
    /**
     * 还可以定义自己的TaskScheduler bean
     */
    @Bean
    public TaskScheduler myScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }
}
