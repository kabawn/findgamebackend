package com.example.findgame.mapper;

import com.example.findgame.dto.UserLocationInteractionDto;
import com.example.findgame.entity.UserLocationInteraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserLocationInteractionMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "gameLocation.id", target = "gameLocationId")
    UserLocationInteractionDto toDto(UserLocationInteraction interaction);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "gameLocationId", target = "gameLocation.id")
    UserLocationInteraction toEntity(UserLocationInteractionDto dto);

}

