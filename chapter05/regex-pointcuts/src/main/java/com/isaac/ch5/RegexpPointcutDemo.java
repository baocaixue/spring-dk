package com.isaac.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class RegexpPointcutDemo {
    public static void main(String[] args){
        Guitarist target = new Guitarist();
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*sing*.");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target);

        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing();
        proxy.sing1();
        proxy.stopSing();
        proxy.rest();
    }
}
