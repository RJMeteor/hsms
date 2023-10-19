package com.primarySchoolPeriod.util.validators.annotation;

import com.primarySchoolPeriod.util.validators.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = {PhoneValidator.class})
@Repeatable(Phone.List.class)
public @interface Phone {
    String message() default "{电话号码错误}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.METHOD})
    @Retention(RUNTIME)
    @interface List {
        Phone[] value();
    }
}
