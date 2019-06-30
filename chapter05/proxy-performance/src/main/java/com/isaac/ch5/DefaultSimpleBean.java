package com.isaac.ch5;

public class DefaultSimpleBean implements SimpleBean{
    private long dummy = 0;

    @Override
    public void advised() {
        dummy = System.currentTimeMillis();
    }

    @Override
    public void unAdvised() {
        dummy = System.currentTimeMillis();
    }
}
