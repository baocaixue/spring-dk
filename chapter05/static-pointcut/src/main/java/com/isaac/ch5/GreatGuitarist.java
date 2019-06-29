package com.isaac.ch5;

import com.isaac.ch2.common.Singer;

public class GreatGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("normal singer sing, didn't need any advice");
    }
}
