package com.isaac.ch16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * WebMvcConfigurer接口通过定义回调方法为Spring MVC定义了通过@EnableWebMvc启动的基于Java的配置
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.isaac.ch16")
public class WebConfig implements WebMvcConfigurer {
    /**
     * 添加了一些处理程序，这些处理程序用于提供来自Web应用程序根目录、类路径和其他特定位置的静态资源，比如图像、JavaScript和CSS文件。
     * 在这个自定义的实现中，对任何包含资源的URL请求都将绕过所有过滤器而由特殊处理程序处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/").setCachePeriod(31556926);
    }

    /**
     * 启用处理静态资源的处理程序
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 定义了用响应状态码或显示响应体的视图预先配置的简单自动化控制器。这些视图没有控制器逻辑，主要用于显示欢迎页面、执行简单的站点URL重定向、返回404状态等。
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/").setViewName("singers/list");
    }

    /**
     * 将符号视图名称与/WEB-INF/views下的*.jspx模板相匹配
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views");
        resolver.setSuffix(".jspx");
        resolver.setRequestContextAttribute("requestContext");
        return resolver;
    }
}
