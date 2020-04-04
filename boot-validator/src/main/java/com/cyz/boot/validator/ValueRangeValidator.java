package com.cyz.boot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @Author:cyz
 * @Date:2020/4/3 20:37
 * @Description:
 */
public class ValueRangeValidator implements ConstraintValidator<ValueRange,Object> {

    private String[] values;

    @Override
    public void initialize(ValueRange constraintAnnotation) {
        values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null){
            return true;
        }
        String v = o.toString();
        for (String s : values){
            if (Objects.equals(s,v)){
                return true;
            }
        }
        return false;
    }
}
