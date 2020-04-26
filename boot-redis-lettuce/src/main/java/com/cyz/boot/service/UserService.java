package com.cyz.boot.service;

import com.cyz.boot.dto.User;
import com.cyz.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author:cyz
 * @Date:2020/4/26 20:38
 * @Description:
 */
@CacheConfig(cacheNames = "user")
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Cacheable(key = "#id")
    public User getUser(Long id){
        return userRepository.getOne(id);
    }

    @CachePut(key="#user.id")
    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    @CachePut(key = "#user.id")
    public User updateUser(User user){
        userRepository.save(user);
        return user;
    }

    @CacheEvict(key = "#id")
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
