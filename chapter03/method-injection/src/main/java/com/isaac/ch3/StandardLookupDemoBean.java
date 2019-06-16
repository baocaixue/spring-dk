package com.isaac.ch3;

public class StandardLookupDemoBean implements DemoBean {
    private Singer singer;

    @Override
    public Singer getMySinger() {
        return singer;
    }

    @Override
    public void doSomething() {
        singer.sing();
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
