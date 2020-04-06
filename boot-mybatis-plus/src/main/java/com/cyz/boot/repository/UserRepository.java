package com.cyz.boot.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyz.boot.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:cyz
 * @Date:2020/4/2 20:27
 * @Description:
 */
@Mapper
public interface UserRepository extends BaseMapper<User> {
}
