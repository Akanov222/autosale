package com.autosale.service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//@Constraint(validatedBy = CarTypeValidator.class)
public @interface ValidCarType {
    String message() default "Invalid car type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
