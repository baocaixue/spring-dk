package com.isaac.ch5;

import com.isaac.ch2.common.Singer;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutDemo {
    public static void main(String[] args){
        GoodGuitarist target1 = new GoodGuitarist();
        GreatGuitarist target2 = new GreatGuitarist();

        Singer proxy1,proxy2;

        Pointcut p = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(p, advice);

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target1);

        proxy1 = (Singer) pf.getProxy();

        pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(target2);

        proxy2 = (Singer) pf.getProxy();

        proxy1.sing();
        proxy2.sing();
    }
}
