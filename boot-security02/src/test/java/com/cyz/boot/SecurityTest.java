package com.cyz.boot;

import com.cyz.boot.dto.Role;
import com.cyz.boot.dto.User;
import com.cyz.boot.dto.UserRole;
import com.cyz.boot.mapper.RoleRepository;
import com.cyz.boot.mapper.UserRepository;
import com.cyz.boot.mapper.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.condition.OS;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sun.nio.cs.US_ASCII;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:cyz
 * @Date:2020/4/27 23:03
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SecurityApplication.class)
public class SecurityTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void initData(){

        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "cyz",
                new BCryptPasswordEncoder().encode("666666")));
        userList.add(new User(2L, "wq",
                new BCryptPasswordEncoder().encode("8888")));

        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(1L,"ROLE_ADMIN"));
        roleList.add(new Role(2L,"ROLE_SYSTEM"));

        List<UserRole> urList = new ArrayList<>();
        urList.add(new UserRole(1L,1L,1L));
        urList.add(new UserRole(2L,1L,2L));
        urList.add(new UserRole(3L,2L,2L));

        userList.forEach(userRepository::insert);
        roleList.forEach(roleRepository::insert);
        urList.forEach(userRoleRepository::insert);
    }
}
