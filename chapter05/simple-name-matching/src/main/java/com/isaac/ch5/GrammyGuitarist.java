package com.isaac.ch5;

import com.isaac.ch2.common.Guitar;
import com.isaac.ch2.common.Singer;

public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("singing... singing... singing...");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest(){
        System.out.println("zzz");
    }

    public void talk(){
        System.out.println("talk");
    }
}
