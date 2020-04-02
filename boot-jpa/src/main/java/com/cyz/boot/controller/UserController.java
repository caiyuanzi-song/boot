package com.cyz.boot.controller;

import com.cyz.boot.domain.User;
import com.cyz.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:cyz
 * @Date:2020/4/2 20:29
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public List<User> add(User user){
        userRepository.save(user);
        return userRepository.findAll();
    }

    @PostMapping("/delete")
    public List<User> delete(Long id){
        userRepository.deleteById(id);
        return userRepository.findAll();
    }

    @GetMapping("/list")
    public List<User> list(){
        return userRepository.findAll();
    }

    @PostMapping("/update")
    public List<User> update(User user){
        userRepository.equals(user);
        return userRepository.findAll();
    }
}
