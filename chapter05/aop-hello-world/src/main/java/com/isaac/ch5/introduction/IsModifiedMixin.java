package com.isaac.ch5.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {
    private boolean isModified;

    private Map<Method, Method> methodCache = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //如果本来就是修改过的，就不用处理了
        if (!isModified) {
            if (mi.getMethod().getName().startsWith("set") && mi.getArguments().length ==1) {
                Method getter = getGetter(mi.getMethod());
                if (getter != null) {
                    Object newVal = mi.getArguments()[0];
                    Object oldVal = getter.invoke(mi.getThis(), null);

                    isModified = ((newVal != null) || (oldVal != null)) && (newVal == null || oldVal == null || !newVal.equals(oldVal));
                }
            }
        }
        return super.invoke(mi);
    }

    private Method getGetter(Method setter) {
        Method getter = methodCache.get(setter);
        if (getter != null)
            return getter;
        String getterName = setter.getName().replaceFirst("set", "get");

        try {
            getter = setter.getDeclaringClass().getMethod(getterName, null);
            synchronized (methodCache) {
                methodCache.put(setter, getter);
            }
        } catch (NoSuchMethodException e) {
            return null;
        }
        return getter;
    }
}
