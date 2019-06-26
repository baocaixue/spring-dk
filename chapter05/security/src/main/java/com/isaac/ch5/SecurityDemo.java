package com.isaac.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityDemo {
    public static void main(String[] args){
        SecurityManager sm = new SecurityManager();

        SecureBean bean = getSecureBean();

        try {
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("错误信息: " + e.getMessage());
        }

        sm.login("Isaac", "123456");
        bean.writeSecureMessage();
        sm.logout();

        try {
            sm.login("Isaac", "11111");
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("错误信息: " + e.getMessage());
        } finally {
            sm.logout();
        }
    }

    private static SecureBean getSecureBean() {
        SecurityAdvice advice = new SecurityAdvice();
        SecureBean target = new SecureBean();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(advice);

        return (SecureBean) pf.getProxy();
    }
}
