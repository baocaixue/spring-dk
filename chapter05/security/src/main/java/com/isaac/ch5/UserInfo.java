package com.isaac.ch5;

/**
 * 存储用户凭证的UserInfo
 */
public class UserInfo {
    private String userName;
    private String password;

    public UserInfo(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
