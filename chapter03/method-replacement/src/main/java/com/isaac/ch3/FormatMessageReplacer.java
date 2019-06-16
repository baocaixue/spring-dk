package com.isaac.ch3;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class FormatMessageReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        if (isFormatMessageMethod(method))
            return "<h2>" + args[0] + "</h2>";
        throw new IllegalArgumentException("Unable to reimplement method " + method.getName());
    }

    private boolean isFormatMessageMethod(Method method) {
        return method.getParameterTypes().length == 1 && "formatMessage".equals(method.getName()) && method.getReturnType() == String.class && method.getParameterTypes()[0] == String.class;
    }
}
