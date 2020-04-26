package com.cyz.boot.controller;

import com.cyz.boot.dto.User;
import com.cyz.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:cyz
 * @Date:2020/4/26 21:13
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String save(User user){
        try {
            userService.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "ok";
    }

    @PostMapping("/update")
    public String update(User user){
        try {
            userService.updateUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "ok";
    }

    @GetMapping("/get")
    public User get(Long id){
        User user = new User();
        try {
            user = userService.getUser(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @PostMapping("/delete")
    public String delete(Long id){
        try {
            userService.deleteUser(id);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "ok";
    }
}
