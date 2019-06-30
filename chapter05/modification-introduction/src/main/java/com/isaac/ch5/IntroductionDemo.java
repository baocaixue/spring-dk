package com.isaac.ch5;

import com.isaac.ch2.common.Contact;
import com.isaac.ch5.introduction.IsModified;
import com.isaac.ch5.introduction.IsModifiedAdvisor;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class IntroductionDemo {
    public static void main(String[] args){
        Contact target = new Contact();
        target.setName("Isaac");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setOptimize(true);

        Contact proxy = (Contact) pf.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Has been modified?: " +
                proxyInterface.isModified());

        proxy.setName("Isaac");

        System.out.println("Has been modified?: " +
                proxyInterface.isModified());

        proxy.setName("Eric");

        System.out.println("Has been modified?: " +
                proxyInterface.isModified());

    }
}
