package com.isaac.ch10;

import com.isaac.ch10.obj.Singer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountrySingerValidator implements ConstraintValidator<CheckCountrySinger, Singer> {
    @Override
    public void initialize(CheckCountrySinger constraintAnnotation) {

    }

    @Override
    public boolean isValid(Singer value, ConstraintValidatorContext context) {
        return value.getGenre() == null || (!value.isCountrySinger() || (value.getLastName() != null && value.getGender() != null));
    }
}
