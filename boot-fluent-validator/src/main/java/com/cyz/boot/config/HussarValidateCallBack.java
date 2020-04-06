package com.cyz.boot.config;

import com.baidu.unbiz.fluentvalidator.DefaultValidateCallback;
import com.baidu.unbiz.fluentvalidator.ValidateCallback;
import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.validator.element.ValidatorElementList;

import java.util.List;

/**
 * @Author:cyz
 * @Date:2020/4/6 17:23
 * @Description:
 */
public class HussarValidateCallBack extends DefaultValidateCallback implements ValidateCallback {
    @Override
    public void onSuccess(ValidatorElementList validatorElementList) {
        super.onSuccess(validatorElementList);
    }

    @Override
    public void onFail(ValidatorElementList validatorElementList, List<ValidationError> errors) {
        throw new RuntimeException(errors.get(0).getErrorMsg());
    }

    @Override
    public void onUncaughtException(Validator validator, Exception e, Object target) throws Exception {
        throw new RuntimeException(e);
    }
}
