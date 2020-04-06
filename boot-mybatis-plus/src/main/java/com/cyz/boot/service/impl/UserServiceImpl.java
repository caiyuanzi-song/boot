package com.cyz.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyz.boot.domain.User;
import com.cyz.boot.repository.UserRepository;
import com.cyz.boot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author:cyz
 * @Date:2020/4/6 14:54
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {
}
