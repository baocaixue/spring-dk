package com.isaac.ch5;

import org.springframework.stereotype.Component;

@Component("isaac")
public class GrammyGuitarist{
    public void sing() {
        System.out.println("sing... sing... sing...");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    @Demo
    public void rest(String arg){
        System.out.println("zzz");
    }

    public void talk(){
        System.out.println("talk");
    }
}

