package com.isaac.ch5;

/**
 * 验证用户，存储凭据
 */
public class SecurityManager {
    /*使用ThreadLocal将UserInfo存储在当前线程中*/
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public void login(String userName, String password) {
        threadLocal.set(new UserInfo(userName, password));
    }

    public void logout() {
        threadLocal.set(null);
    }

    public UserInfo getLoggedOnUser() {
        return threadLocal.get();
    }
}
