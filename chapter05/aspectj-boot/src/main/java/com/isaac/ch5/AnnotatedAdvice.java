package com.isaac.ch5;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotatedAdvice {
    @Pointcut("execution(* com.isaac.ch5.sing*(com.isaac.ch5.Guitar)) && args(value)")
    public void singExecution(Guitar value) {
    }

    @Pointcut("bean(isaac*)")
    public void isIsaac() {
    }

    @Before("singExecution(value) && isIsaac()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value) {
        if(value.getBrand().equals("Gibson")) {
            System.out.println("Executing: " +
                    joinPoint.getSignature().getDeclaringTypeName() + " "
                    + joinPoint.getSignature().getName() + " argument: " + value.getBrand());
        }
    }

    @Around("singExecution(value) && isIsaac()")
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp, Guitar value) throws Throwable {
        System.out.println("Before execution: " +
                pjp.getSignature().getDeclaringTypeName() + " "
                + pjp.getSignature().getName()
                + " argument: " + value.getBrand());

        Object retVal = pjp.proceed();

        System.out.println("After execution: " +
                pjp.getSignature().getDeclaringTypeName() + " "
                + pjp.getSignature().getName()
                + " argument: " + value.getBrand());

        return retVal;
    }
}
