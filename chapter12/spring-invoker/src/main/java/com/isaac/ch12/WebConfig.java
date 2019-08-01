package com.isaac.ch12;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 由于通过Spring MVC公开HTTP调用器，因此需要配置Web应用程序。
 * 该配置类实现了WebMvcConfigurer接口。该接口，定义了一些回调方法来为通过使用@EnableWebMvc启用的Spring MVC定义基于Java的配置。
 *
 * 这里只需要公开一个HTTP服务（不需要Web接口），所以只要一个空的实现
 */
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
}
