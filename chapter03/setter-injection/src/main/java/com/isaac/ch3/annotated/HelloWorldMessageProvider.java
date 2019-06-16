package com.isaac.ch3.annotated;

import com.isaac.ch2.decoupled.MessageProvider;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello world";
    }
}
