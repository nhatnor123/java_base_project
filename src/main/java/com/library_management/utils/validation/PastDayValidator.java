package com.library_management.utils.validation;

import com.library_management.utils.DateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.ZonedDateTime;

public class PastDayValidator implements ConstraintValidator<PastDayConstraint, ZonedDateTime> {
    @Override
    public void initialize(PastDayConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(ZonedDateTime input, ConstraintValidatorContext constraintValidatorContext) {
        return input == null || input.isBefore(DateUtil.now());
    }
}
