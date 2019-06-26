package com.isaac.ch5;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice{
    /*不需要和应用程序共享相同的SecurityAdvice实例，因为所有数据都通过ThreadLocal存储在当前线程中*/
    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }


    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo user = securityManager.getLoggedOnUser();
        if(user == null){
            System.out.println("请登录！！！");
            throw new SecurityException("必须登录之后才能访问方法： " + method.getName());
        } else if("Isaac".equals(user.getUserName()) && "123456".equals(user.getPassword())) {
            System.out.println("登录成功，欢迎" + user.getUserName() + " 以下是方法执行情况...");
        } else{
            System.out.println("密码或用户名错误！");
            throw new SecurityException("用户名：" + user.getUserName() + "与您输入的密码不匹配");
        }

    }
}
