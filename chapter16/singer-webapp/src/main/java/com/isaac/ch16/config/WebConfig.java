package com.isaac.ch16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

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
     *
     * 例如，对于URL http://localhost:8080/resources/styles/standard.css，SpringMVC将在src/main/webapp/style文件夹中检索standard.css
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

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //国际化拦截器
        registry.addInterceptor(localeChangeInterceptor());
        //主题拦截器
        registry.addInterceptor(themeChangeInterceptor());
    }

    /**
     * 拦截每个更改主题的请求
     */
    @Bean
    public ThemeChangeInterceptor themeChangeInterceptor() {
        return new ThemeChangeInterceptor();
    }

    /**
     * SpringMVC拦截器，支持使用可配置的请求参数进行语言切换，在该拦截器配置中，名为lang的URL参数是为更改应用程序的语言环境而定义的
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * ReloadableResourceBundleMessageSource实现了MessageSource接口，从定义的文件（WEB-INF/il18n下）中加载消息，以支持国际化
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("WEB-INF/i18n/messages", "WEB-INF/i18n/application");
        messageSource.setDefaultEncoding("UTF-8");
        //指示SpringMVC是否在客户端语言环境的特殊资源包未找到时回退到应用程序运行时的系统语言环境
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    /**
     * 校验Bean
     */
    @Bean
    public Validator validator() {
        final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    /**
     * 从浏览器的Cookie中存储和检索语言环境设置
     */
    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        cookieLocaleResolver.setCookieMaxAge(3600);
        cookieLocaleResolver.setCookieName("locale");
        return cookieLocaleResolver;
    }

    /**
     * 加载活动主题bean
     */
    @Bean
    public ResourceBundleThemeSource themeSource() {
        return new ResourceBundleThemeSource();
    }

    /**
     * 解析用户活动主题bean
     */
    @Bean
    public CookieThemeResolver themeResolver() {
        CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
        cookieThemeResolver.setDefaultThemeName("standard");
        cookieThemeResolver.setCookieMaxAge(3600);
        cookieThemeResolver.setCookieName("theme");
        return cookieThemeResolver;
    }
}
