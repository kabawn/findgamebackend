package com.example.findgame.mapper;

import com.example.findgame.dto.GameLocationDto;
import com.example.findgame.entity.GameLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GameLocationMapper {

    GameLocationMapper INSTANCE = Mappers.getMapper(GameLocationMapper.class);

    @Mapping(source = "editor.id", target = "editorId")
    GameLocationDto toDto(GameLocation gameLocation);

    GameLocation toEntity(GameLocationDto gameLocationDto);
}

