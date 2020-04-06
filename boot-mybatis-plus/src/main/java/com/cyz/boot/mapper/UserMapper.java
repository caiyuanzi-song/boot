package com.cyz.boot.mapper;

import com.cyz.boot.domain.User;
import com.cyz.boot.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author:cyz
 * @Date:2020/4/5 20:02
 * @Description:
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "mobile",target = "userName")
    @Mapping(source = "id",target = "id")
    User dtoToEntity(UserDTO dto);
}
