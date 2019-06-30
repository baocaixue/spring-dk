package com.isaac.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ControlFlowDemo {
    public static void main(String[] args){
        TestBean target = new TestBean();
        Pointcut pointcut = new ControlFlowPointcut(ControlFlowDemo.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target);

        TestBean proxy = (TestBean) pf.getProxy();
        System.out.println("\tTrying normal invoke");
        proxy.foo();
        System.out.println("\n\tTrying under ControlFlowDemo.test()");
        test(proxy);
    }

    private static void test(TestBean bean) {
        bean.foo();
    }
}
