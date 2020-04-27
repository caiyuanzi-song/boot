package com.cyz.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyz.boot.dto.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:cyz
 * @Date:2020/4/27 22:48
 * @Description:
 */
@Mapper
public interface UserRoleRepository extends BaseMapper<UserRole> {
}
