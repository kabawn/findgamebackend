package com.example.findgame.mapper;

import com.example.findgame.dto.ContentDTO;
import com.example.findgame.entity.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "balise.id", target = "baliseId")
    @Mapping(source = "imageUrl", target = "imageUrl") // Explicit, but usually not necessary

    ContentDTO toDto(Content content);

    Content toEntity(ContentDTO contentDTO);
}
