package com.cyz.boot.repository;

import com.cyz.boot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:cyz
 * @Date:2020/4/2 20:27
 * @Description:
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
