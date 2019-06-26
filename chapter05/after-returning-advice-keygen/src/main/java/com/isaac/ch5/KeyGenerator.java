package com.isaac.ch5;

import java.util.Random;

/**
 * 一个非常原始的密钥生成器
 * 模拟密钥生成器有概率生成弱密钥
 */
public class KeyGenerator {
    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF03F590AE56L;

    private Random random = new Random();

    public long getKey() {
        int x = random.nextInt(3);

        if(x == 1) {
            return WEAK_KEY;
        }

        return STRONG_KEY;
    }
}
