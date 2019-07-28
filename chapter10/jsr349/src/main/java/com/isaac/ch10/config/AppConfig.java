package com.isaac.ch10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(basePackages = {"com.isaac.ch10"})
public class AppConfig {
    /**
     * 为了在Spring的ApplicationContext中配置对Bean Validation API的支持，可以在Spring的配置文件中定义一个类型为
     * org.springframework.validation.beanvalidation.LocalValidatorFactoryBean的Bean
     *
     * 一旦定义了该Bean，就可以在应用程序的任何位置创建Validator接口的句柄
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
