package com.isaac.ch5;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    /**
     * Can't control method invoke
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before '" + method.getName() + "', tune guitar.");
    }

    public static void main(String[] args){
        Guitarist target = new Guitarist();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        SimpleBeforeAdvice advice = new SimpleBeforeAdvice();
        proxyFactory.addAdvice(advice);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
    }
}
