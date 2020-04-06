package com.cyz.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyz.boot.domain.User;
import com.cyz.boot.dto.PageDTO;
import com.cyz.boot.dto.UserDTO;
import com.cyz.boot.mapper.UserMapper;
import com.cyz.boot.repository.UserRepository;
import com.cyz.boot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author:cyz
 * @Date:2020/4/5 20:05
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation(value = "新增用户",notes = "新增用户",httpMethod = "POST")
    public User save(@Valid UserDTO dto){
        User user = UserMapper.INSTANCE.dtoToEntity(dto);
        userService.save(user);
        return user;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除用户",notes = "删除用户",httpMethod = "POST")
    public List<User> delete(Long id){
        userService.removeById(id);
        return userService.list();
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询用户",notes = "查询用户",httpMethod = "GET")
    public List<User> list(){
        return userService.list();
    }

    @ApiOperation(value = "修改用户",notes = "修改用户",httpMethod = "POST")
    @PostMapping("/update")
    public List update(UserDTO dto){
        User user = UserMapper.INSTANCE.dtoToEntity(dto);
        userService.updateById(user);
        return userService.list();
    }

    @GetMapping("/search")
    @ApiOperation(value = "检索用户",httpMethod = "GET")
    public IPage<User> search(PageDTO dto){
        return userService.page(new Page<>(dto.getPageNum(),dto.getPageSize()));
    }
}
