package com.isaac.ch10;

import com.isaac.ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class SpringValidatorDemo {
    private static Logger logger = LoggerFactory.getLogger(SpringValidatorDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Singer singer = new Singer();

        Validator validator = ctx.getBean(Validator.class);
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(singer, "test");

        ValidationUtils.invokeValidator(validator, singer, result);

        List<ObjectError> errors = result.getAllErrors();
        logger.info("How many errors : " + errors.size());

        errors.forEach(e -> logger.info(e.getCode()));

        ctx.close();
    }
}
