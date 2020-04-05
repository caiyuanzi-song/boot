package com.cyz.boot.controller;

import com.cyz.boot.domain.User;
import com.cyz.boot.dto.UserDTO;
import com.cyz.boot.mapper.UserMapper;
import com.cyz.boot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author:cyz
 * @Date:2020/4/5 20:05
 * @Description:
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public User save(@Valid UserDTO dto){
        User user = UserMapper.INSTANCE.dtoToEntity(dto);
        return userRepository.save(user);
    }
}
