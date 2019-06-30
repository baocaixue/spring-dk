package com.isaac.ch5;

import com.isaac.ch2.common.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ComposablePointcutDemo {
    public static void main(String[] args){
        GrammyGuitarist target = new GrammyGuitarist();
        ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        System.out.println("Test 1 sing >>");
        GrammyGuitarist proxy = getProxy(pointcut, target);
        testInvoke(proxy);
        System.out.println();

        System.out.println("Test2 sing union talk >>");
        //'union' just like sql 'or'
        pointcut.union(new TalkMethodMatcher());
        proxy = getProxy(pointcut, target);
        testInvoke(proxy);
        System.out.println();

        System.out.println("Test3 sing union talk intersection rest");//---> none advice method
        //'intersection' just like sql 'and'
        pointcut.intersection(new RestMethodMatcher());
        proxy = getProxy(pointcut, target);
        testInvoke(proxy);
        System.out.println();
    }

    private static void testInvoke(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.talk();
        proxy.rest();
    }

    private static GrammyGuitarist getProxy(ComposablePointcut pointcut, GrammyGuitarist target) {
        Advisor advisor = new DefaultPointcutAdvisor(pointcut,
                new SimpleBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        return (GrammyGuitarist) pf.getProxy();
    }
}
