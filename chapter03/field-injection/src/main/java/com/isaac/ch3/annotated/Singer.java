package com.isaac.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Singer {
    @Autowired
    private Inspiration inspiration;

    public void sing() {
        System.out.println(inspiration.getLyric());
    }
}
