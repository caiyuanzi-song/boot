package com.cyz.boot.validator;

import org.hibernate.annotations.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author:cyz
 * @Date:2020/4/3 20:36
 * @Description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueRangeValidator.class)
public @interface ValueRange {
    String[] values();

    String message() default "值不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
