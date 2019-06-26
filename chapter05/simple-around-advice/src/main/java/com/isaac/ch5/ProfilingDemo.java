package com.isaac.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {
    public static void main(String[] args){
        WorkBean bean = getWorkBean();
        bean.doSomeWork(10000000);
    }

    private static WorkBean getWorkBean() {
        WorkBean target = new WorkBean();
        ProfilingInterceptor advice = new ProfilingInterceptor();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(advice);
        return (WorkBean) pf.getProxy();
    }
}
