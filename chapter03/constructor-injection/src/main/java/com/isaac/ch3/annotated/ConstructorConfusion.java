package com.isaac.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ConstructorConfusion {
    private String someValue;

    public ConstructorConfusion(String someValue) {
        System.out.println("ConstructorConfusion(String) called");
        this.someValue = someValue;
    }

    @Autowired
    public ConstructorConfusion(@Value("99") int someValue) {
        System.out.println("ConstructorConfusion(int) called");
        this.someValue = Integer.toString(someValue);
    }

    @Override
    public String toString() {
        return someValue;
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();

        ConstructorConfusion cc = ctx.getBean("constructorConfusion", ConstructorConfusion.class);
        System.out.println(cc);

        ctx.close();
    }
}
