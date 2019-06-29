package com.isaac.ch5;

import com.isaac.ch2.common.Singer;

public class Guitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("singing ... singing ...");
    }

    public void sing1() {
        System.out.println("special part performance, singing ...");
    }

    public void stopSing() {
        System.out.println("stop sing ... ");
    }

    public void rest() {
        System.out.println("zzz");
    }
}
