package com.isaac.ch3.annotated;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 在Spring的众多注解中，经常会发现很多注解的不同属性起着相同的作用，比如@RequestMapping的value属性和path属性，这就需要做一些基本的限制，
 * 比如value和path的值不能冲突，比如任意设置value或者设置path属性的值，都能够通过另一个属性来获取值等等。为了统一处理这些情况，Spring创建了@AliasFor标签
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Award {
    @AliasFor("prize")
    String[] value() default {};

    @AliasFor("value")
    String[] prize() default {};
}
