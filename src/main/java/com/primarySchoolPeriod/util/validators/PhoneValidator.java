package com.primarySchoolPeriod.util.validators;

import com.primarySchoolPeriod.util.validators.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone,String> {


    private final  Pattern phone = Pattern.compile("^((13\\d{9}$)|(15[0,1,2,3,5,6,7,8,9]\\d{8}$)|(18[0,2,5,6,7,8,9]\\d{8}$)|(147\\d{8})$)");

    /**
     * 获取注解内的属性值
     * @param constraintAnnotation
     */
    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    /**
     * 是否通过校验
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        Matcher m = phone.matcher(value);
        return m.matches();
    }
}