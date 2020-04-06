package com.cyz.boot.controller;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.cyz.boot.dto.UserDTO;
import com.cyz.boot.utils.RestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:cyz
 * @Date:2020/4/6 17:42
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @PostMapping("/save")
    public RestData<UserDTO> save(@FluentValid @RequestBody UserDTO dto){
        return new RestData<UserDTO>().success("",dto);
    }
}
