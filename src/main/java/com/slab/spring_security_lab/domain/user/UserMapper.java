package com.slab.spring_security_lab.domain.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    public static UserMapper Instance = Mappers.getMapper(UserMapper.class);

    User toModel(UserEntity entity);
}
