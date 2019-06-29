package com.isaac.ch5;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class DynamicPointcutDemo {
    public static void main(String[] args){
        SimpleBean target = new SimpleBean();
        Advice advice = new SimpleAdvice();
        Pointcut pointcut = new SimpleDynamicPointcut();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        SimpleBean proxy = (SimpleBean) pf.getProxy();

        proxy.foo(1);//advice
        proxy.foo(100);//no advice
        proxy.bar();//no advice
    }
}
