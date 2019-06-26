package com.isaac.ch5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.util.Arrays;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch("性能分析");
        sw.start("目标对象：" + invocation.getThis() + "-方法:" + invocation.getMethod().getName() + "-参数：" + Arrays.toString(invocation.getArguments()));
        Object returnVal = invocation.proceed();
        sw.stop();
        System.out.println(sw.prettyPrint());
        return returnVal;
    }
}
