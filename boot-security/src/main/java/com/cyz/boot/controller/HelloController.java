package com.cyz.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:cyz
 * @Date:2020/4/27 21:45
 * @Description:
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello Cyz";
    }
}
