package com.unity.stripe.payments.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueEmail {

    String message() default "Email Address Already Exist or Invalid Email**";//Unique Email Constraint violated

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
