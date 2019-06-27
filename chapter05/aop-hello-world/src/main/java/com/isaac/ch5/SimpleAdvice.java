package com.isaac.ch5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(">> Invoking " + invocation.getThis() + "." + invocation.getMethod());
        Object result = invocation.proceed();
        System.out.println(">> Done\n");
        return result;
    }
}
