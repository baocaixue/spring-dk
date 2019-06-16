package com.isaac.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Target {
    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public Target() {
    }

    public Target(Foo foo) {
        System.out.println("Target(Foo) called");
    }

    public Target(Foo foo, Bar bar) {
        System.out.println("Target(Foo, Bar) called");
    }

    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;
        System.out.println("Property fooOne set");
    }

    public void setFooTwo(Foo foo) {
        this.fooTwo = foo;
        System.out.println("Property fooTwo set");
    }

    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("Property bar set");
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-03.xml");
        ctx.refresh();

        Target t = null;

        System.out.println("Using byName:");
        t = ctx.getBean("targetByName", Target.class);

        System.out.println("\nUsing byType:");
        t = ctx.getBean("targetByType", Target.class);

        System.out.println("\nUsing constructor:");
        t = ctx.getBean("targetConstructor", Target.class);
        ctx.close();
    }
}
