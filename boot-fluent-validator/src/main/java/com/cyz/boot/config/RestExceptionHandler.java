package com.cyz.boot.config;

import com.cyz.boot.utils.RestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author:cyz
 * @Date:2020/4/6 17:26
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 校验错误拦截处理
     * 使用 @RequestBody 接收入参时，校验失败抛 MethodArgumentNotValidException 异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestData hanler(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException handler",e);
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasFieldErrors()){
            return RestData.build().error(bindingResult.getFieldError().getDefaultMessage());
        }
        return RestData.build().error("parameter is not valid");
    }

    /**
     * 校验错误拦截处理
     * 使用 @RequestBody 接收入参时，数据类型转换失败抛 HttpMessageConversionException 异常
     */
    @ExceptionHandler(value = HttpMessageConversionException.class)
    public RestData handler(HttpMessageConversionException e){
        log.error("HttpMessageConversionException handler",e);
        return RestData.build().error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public RestData handler(Exception e){
        log.error("Exception handler",e);
        return RestData.build().error(e.getMessage());
    }
}
