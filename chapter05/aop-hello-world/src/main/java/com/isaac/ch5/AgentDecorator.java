package com.isaac.ch5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 为Agent.speak()连接点实现环绕通知
 * Aopalliance 接口MethodInterceptor用于实现方法调用连接点的环绕通知
 */
public class AgentDecorator implements MethodInterceptor{
    @Override
    public Object invoke(/*正在被通知的方法调用*/MethodInvocation invocation) throws Throwable {
        System.out.print("James ");
        Object retVal = invocation.proceed();
        System.out.println("!");
        return retVal;
    }
}
