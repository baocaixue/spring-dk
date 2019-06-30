package com.isaac.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ProxyPerfTest {
    public static void main(String[] args){
        SimpleBean target = new DefaultSimpleBean();
        Advisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());

        runCglibTests(advisor, target);
        /*性能提升明显的frozen模式*/
        runCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }

    private static void runJdkTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = setCommonProxyFactory(advisor, target);
        /*指示使用JDK代理*/
        pf.setInterfaces(SimpleBean.class);
        SimpleBean proxy = (SimpleBean)pf.getProxy();
        System.out.println("Running JDK Tests");
        test(proxy);
    }

    private static void runCglibFrozenTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = setCommonProxyFactory(advisor, target);
        pf.setProxyTargetClass(true);
        pf.setFrozen(true);

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("Running CGLIB (Frozen) Tests");
        test(proxy);
    }

    private static void runCglibTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = setCommonProxyFactory(advisor,target);
        pf.setProxyTargetClass(true);
        SimpleBean proxy = (SimpleBean)pf.getProxy();
        System.out.println("Running CGLIB (Standard) Tests");
        test(proxy);
    }

    private static void test(SimpleBean bean) {
        long before = 0;
        long after = 0;

        System.out.println("Testing Advised Method");
        before = System.nanoTime();
        for(int x = 0; x < 500000; x++) {
            bean.advised();
        }
        after = System.nanoTime();

        System.out.println("Took " + (after - before) / 1000000 + " ms");

        System.out.println("Testing Unadvised Method");
        before = System.nanoTime();
        for(int x = 0; x < 500000; x++) {
            bean.unAdvised();
        }
        after = System.nanoTime();

        System.out.println("Took " + (after - before) / 1000000 + " ms");

        System.out.println("Testing equals() Method");
        before = System.nanoTime();
        for(int x = 0; x < 500000; x++) {
            bean.equals(bean);
        }
        after = System.nanoTime();

        System.out.println("Took " + (after - before) / 1000000 + " ms");

        System.out.println("Testing hashCode() Method");
        before = System.nanoTime();
        for(int x = 0; x < 500000; x++) {
            bean.hashCode();
        }
        after = System.nanoTime();

        System.out.println("Took " + (after - before) / 1000000 + " ms");

        /*创建后修改dialing*/
        Advised advised = (Advised)bean;

        System.out.println("Testing Advised.getProxyTargetClass() Method");
        before = System.nanoTime();
        for(int x = 0; x < 500000; x++) {
            advised.getTargetClass();
        }
        after = System.nanoTime();

        System.out.println("Took " + (after - before) / 1000000 + " ms");

        System.out.println(">>>\n");
    }

    private static ProxyFactory setCommonProxyFactory(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target);
        return pf;
    }
}
