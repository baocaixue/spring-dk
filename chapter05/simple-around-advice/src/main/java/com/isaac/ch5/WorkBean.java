package com.isaac.ch5;

public class WorkBean {
    public void doSomeWork(int noOfTimes) {
        for (int i = 0; i < noOfTimes; i++) {
            work();
        }
    }

    private void work() {
        //防止编译器优化work()，从而直接调用work()
        System.out.print("");
    }
}
