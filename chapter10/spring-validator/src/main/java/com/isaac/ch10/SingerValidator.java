package com.isaac.ch10;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SingerValidator implements Validator {
    /**
     * 指示验证器是否支持验证传入的类型
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Singer.class.equals(clazz);
    }

    /**
     * 对传入对象进行验证
     * @param target
     * @param errors 验证结果存储在该接口实例中
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
    }
}
