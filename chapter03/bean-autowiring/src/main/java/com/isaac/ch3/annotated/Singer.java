package com.isaac.ch3.annotated;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 加载Spring配置文件时，如果Spring配置文件中所定义的Bean类实现了ApplicationContextAware 接口，那么在加载Spring配置文件时，
 * 会自动调用ApplicationContextAware接口中的
 */
@Component("johnMayer")
//@DependsOn("gopher")
public class Singer implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Singer(){
    }

    public void sing() {
        Guitar guitar = applicationContext.getBean("gopher", Guitar.class);
        guitar.sing();
    }
}
