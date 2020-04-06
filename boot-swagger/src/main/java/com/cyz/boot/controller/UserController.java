package com.cyz.boot.controller;

import com.cyz.boot.domain.User;
import com.cyz.boot.dto.UserDTO;
import com.cyz.boot.mapper.UserMapper;
import com.cyz.boot.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Api("用户管理")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    @ApiOperation(value = "新增用户",notes = "新增用户",httpMethod = "POST")
    public User save(@Valid UserDTO dto){
        User user = UserMapper.INSTANCE.dtoToEntity(dto);
        return userRepository.save(user);
    }
}
