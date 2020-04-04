package com.cyz.boot.controller;

import com.cyz.boot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author:cyz
 * @Date:2020/4/3 19:55
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/add")
    public User add(@Valid User user){
        return user;
    }


    @PostMapping("/addT")
    public User addT(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError:fieldErrors){
                sb.append(fieldError.getDefaultMessage());
                sb.append(",");
            }
            log.debug(sb.toString());
            return null;
        }
        return user;
    }
}
