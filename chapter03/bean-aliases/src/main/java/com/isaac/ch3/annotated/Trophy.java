package com.isaac.ch3.annotated;

import org.springframework.core.annotation.AliasFor;

@Award
public @interface Trophy {

	@AliasFor(annotation = Award.class, attribute = "value")
	String[] name() default {};
}
