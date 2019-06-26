package com.isaac.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceDemo {
    public static void main(String[] args){
        KeyGenerator kg = getKeyGenerator();

        for (int i = 0; i < 10; i++) {
            try{
                System.out.println("Key: " + kg.getKey());
            } catch (SecurityException e) {
                System.out.println("Weak Key Generated!");
            }
        }
    }

    private static KeyGenerator getKeyGenerator() {
        KeyGenerator target = new KeyGenerator();
        WeakKeyCheckAdvice weakKeyCheckAdvice = new WeakKeyCheckAdvice();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(weakKeyCheckAdvice);
        return (KeyGenerator) pf.getProxy();
    }
}
