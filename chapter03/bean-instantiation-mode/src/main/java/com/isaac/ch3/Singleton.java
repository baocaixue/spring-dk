package com.isaac.ch3;

/**
 * 单例——这里的单例与spring的单例还是有所区别的
 */
public class Singleton {
    private static Singleton instance;

    static {
        instance = new Singleton();
    }

    public static Singleton getInstance() {
        return instance;
    }

    private Singleton() {
    }
}
