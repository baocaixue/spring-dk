package com.isaac.ch3.xml;

public class Singer {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "\tName: " + name + "\n\t" + "Age: " + age;
    }
}
