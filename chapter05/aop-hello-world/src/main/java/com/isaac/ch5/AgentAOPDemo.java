package com.isaac.ch5;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 将AgentDecorator通知织入代码
 */
public class AgentAOPDemo {
    public static void main(String[] args){
        Agent target = new Agent();

        //使用ProxyFactory类创建目标对象的代理，同时织入通知
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new AgentDecorator());
        pf.setTarget(target);

        Agent proxy = (Agent) pf.getProxy();

        target.speak();
        System.out.println();
        proxy.speak();
    }
}
