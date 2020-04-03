package com.cyz.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author:cyz
 * @Date:2020/4/3 16:24
 * @Description:
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String ,Object> hello(@RequestBody Map<String ,Object> map){
        System.out.println(map.toString());
        return map;
    }
}
