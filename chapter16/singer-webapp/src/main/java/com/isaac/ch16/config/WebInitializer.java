package com.isaac.ch16.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final long MAX_FILE_SIZE = 5000000;
    // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 5000000;

    // Size threshold after which files will be written to disk
    private static final int FILE_SIZE_THRESHOLD = 0;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DataServiceConfig.class,SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding("UTF-8");
        cef.setForceEncoding(true);//new HiddenHttpMethodFilter(),
        return new Filter[]{ cef};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    private MultipartConfigElement getMultipartConfigElement() {
        return  new MultipartConfigElement(
                null, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
    }
}
