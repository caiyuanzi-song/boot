package com.cyz.boot.repository;

import com.cyz.boot.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:cyz
 * @Date:2020/4/26 20:38
 * @Description:
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
