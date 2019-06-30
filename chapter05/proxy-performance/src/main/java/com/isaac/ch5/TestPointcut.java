package com.isaac.ch5;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class TestPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "advise".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return cls -> cls == SimpleBean.class;
    }
}
