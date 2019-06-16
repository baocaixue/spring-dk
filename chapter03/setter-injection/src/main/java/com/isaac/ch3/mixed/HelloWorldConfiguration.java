package com.isaac.ch3.mixed;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = "classpath:spring/app-context-xml.xml")
public class HelloWorldConfiguration {
}
