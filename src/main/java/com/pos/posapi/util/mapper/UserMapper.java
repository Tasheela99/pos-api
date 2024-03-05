package com.pos.posapi.util.mapper;

import com.pos.posapi.dto.UserDTO;
import com.pos.posapi.enity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDto(User user);
    User toUser(UserDTO userDTO);

}
