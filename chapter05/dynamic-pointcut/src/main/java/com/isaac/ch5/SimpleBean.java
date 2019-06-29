package com.isaac.ch5;

public class SimpleBean {
    /*本例只通知foo方法, 条件参数不等于100*/
    public void foo(int x) {
        System.out.println("Invoked foo() param is " + x);
    }

    public void bar() {
        System.out.println("Invoked bar()");
    }
}
