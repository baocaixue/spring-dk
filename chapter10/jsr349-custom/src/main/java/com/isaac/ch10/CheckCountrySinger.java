package com.isaac.ch10;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)//表明注解只应用于类级别
@Constraint(validatedBy = CountrySingerValidator.class)//表明这是一个验证器，指定提供验证逻辑的类
@Documented
public @interface CheckCountrySinger {
    //定义违反约束条件时返回的消息（或错误代码）
    String message() default "Country Singer Should Have Gender And Last Name Defined";

    //指定适用的验证组。可以将验证器分配给不同的组，并对特定组执行验证
    Class<?>[] groups() default {};

    //指定其他有效载荷对象（即实现了javax.validation.Payload接口的类）。它允许将附加信息附加到约束上（例如，有效载荷对象可以指明违反约束的严重性）
    Class<? extends Payload>[] payload() default {};
}
