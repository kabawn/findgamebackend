package com.example.findgame.mapper;

import com.example.findgame.dto.BaliseDTO;
import com.example.findgame.entity.Balise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BaliseMapper {

    BaliseMapper INSTANCE = Mappers.getMapper(BaliseMapper.class);

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "searchRadius", target = "searchRadius"),
            @Mapping(source = "name",target = "name")
    })
    BaliseDTO baliseToBaliseDTO(Balise balise);

    @Mappings({
            @Mapping(source = "userId", target = "user.id"),
            @Mapping(source = "searchRadius", target = "searchRadius"),
            @Mapping(source = "name",target = "name")

    })
    Balise baliseDTOToBalise(BaliseDTO baliseDTO);
}
