package com.isaac.ch5;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleAfterReturningAdvice implements AfterReturningAdvice{
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("After '" + method.getName() + "' put down guitar.");
    }

    public static void main(String[] args){
        Guitarist target = new Guitarist();
        SimpleAfterReturningAdvice afterReturningAdvice = new SimpleAfterReturningAdvice();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(afterReturningAdvice);
        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing();
    }
}
