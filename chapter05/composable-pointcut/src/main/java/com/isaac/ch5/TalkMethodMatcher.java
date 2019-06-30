package com.isaac.ch5;

import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class TalkMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "talk".equals(method.getName());
    }
}
