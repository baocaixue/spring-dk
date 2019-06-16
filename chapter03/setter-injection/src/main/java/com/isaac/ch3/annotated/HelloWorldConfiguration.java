package com.isaac.ch3.annotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.isaac.ch3.annotated"})
public class HelloWorldConfiguration {
}
