package com.cyz.boot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cyz.boot.dto.Role;
import com.cyz.boot.dto.User;
import com.cyz.boot.dto.UserRole;
import com.cyz.boot.mapper.RoleRepository;
import com.cyz.boot.mapper.UserRepository;
import com.cyz.boot.mapper.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:cyz
 * @Date:2020/4/27 22:48
 * @Description:
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(userName)){
            throw new UsernameNotFoundException("用户名不能为空");
        }
        User user = userRepository.selectOne(
                new QueryWrapper<User>().lambda().eq(User::getUsername,userName)
        );
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<UserRole> userRoles = userRoleRepository.selectList(
                new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId,user.getId())
        );
        if (userRoles != null && !userRoles.isEmpty()){
            List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roles = roleRepository.selectList(
                    new QueryWrapper<Role>().lambda().in(Role::getId,roleIds)
            );
            user.setRoleList(roles);
        }
        return user;
    }
}
