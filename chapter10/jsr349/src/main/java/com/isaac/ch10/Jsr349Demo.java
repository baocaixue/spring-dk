package com.isaac.ch10;

import com.isaac.ch10.config.AppConfig;
import com.isaac.ch10.obj.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class Jsr349Demo {
    private static Logger logger = LoggerFactory.getLogger(Jsr349Demo.class);

    public static void main(String[] args){
        GenericApplicationContext ctx  = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerValidationService singerValidationService = ctx.getBean(SingerValidationService.class);

        Singer singer = new Singer();
        singer.setFirstName("T");
        singer.setLastName("Mayer");
        singer.setGenre(null);
        singer.setGender(null);

        validateSinger(singer, singerValidationService);

        ctx.close();
    }

    private static void validateSinger(Singer singer, SingerValidationService singerValidationService) {
        Set<ConstraintViolation<Singer>> violations = singerValidationService.validateSinger(singer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Singer>> violations) {
        logger.info("No. of violations: " + violations.size());
        violations.forEach(violation -> {
            logger.info("Validation error for property: " +
                    violation.getPropertyPath()
                    + " with value: " + violation.getInvalidValue()
                    + " with error message: " + violation.getMessage());
        });
    }
}
