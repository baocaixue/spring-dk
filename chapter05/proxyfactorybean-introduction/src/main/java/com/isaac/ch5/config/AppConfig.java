package com.isaac.ch5.config;

import com.isaac.ch2.common.Contact;
import com.isaac.ch5.introduction.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Contact guitarist() {
        Contact guitarist = new Contact();
        guitarist.setName("Isaac");
        return guitarist;
    }

    @Bean
    public Advisor advisor() {
        return new IsModifiedAdvisor();
    }

    @Bean
    ProxyFactoryBean bean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(guitarist());
        //proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setOptimize(true);
        proxyFactoryBean.addAdvisor(advisor());
        return proxyFactoryBean;
    }
}
