package com.indistudia.botbackend.mappers;

import com.indistudia.botbackend.controller.dto.UserDto;
import com.indistudia.botbackend.domain.User;
import com.indistudia.botbackend.dto.CreateUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toUser(CreateUserDto createUserDto);

    UserDto toDto(User user);
}
