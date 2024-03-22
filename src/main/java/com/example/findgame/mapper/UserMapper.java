package com.example.findgame.mapper;

import com.example.findgame.dto.UserDto;
import com.example.findgame.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "searchRadius", source = "searchRadius")
    User toEntity(UserDto dto);

    @Mapping(target = "searchRadius", source = "searchRadius")
    UserDto toDto(User user);
}
