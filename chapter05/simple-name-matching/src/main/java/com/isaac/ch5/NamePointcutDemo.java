package com.isaac.ch5;

import com.isaac.ch2.common.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * 仅根据方法名称进行匹配时，应避免创建staticMethodMatcherPointcut的子类，应使用NameMatchMethodPointcut(它是staticMethodMatcherPointcut的子类)
 * 或使用NameMatchMethodPointcutAdvisor
 */
public class NamePointcutDemo {
    public static void main(String[] args){
        GrammyGuitarist target = new GrammyGuitarist();
        usingNameMatchMethodPointcut(target);
        usingNameMatchMethodPointcutAdvisor(target);
    }

    private static void usingNameMatchMethodPointcut(GrammyGuitarist target) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("sing");//advice sing function whatever params or return
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target);

        GrammyGuitarist proxy = (GrammyGuitarist) pf.getProxy();
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }

    private static void usingNameMatchMethodPointcutAdvisor(GrammyGuitarist target) {
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        advisor.addMethodName("talk");
        advisor.addMethodName("rest");
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        GrammyGuitarist proxy = (GrammyGuitarist) pf.getProxy();
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }

}
