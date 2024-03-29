package com.example.findgame.mapper;

import com.example.findgame.dto.GameLocationDto;
import com.example.findgame.entity.GameLocation;
import com.example.findgame.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-29T02:19:18+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class GameLocationMapperImpl implements GameLocationMapper {

    @Override
    public GameLocationDto toDto(GameLocation gameLocation) {
        if ( gameLocation == null ) {
            return null;
        }

        GameLocationDto gameLocationDto = new GameLocationDto();

        gameLocationDto.setEditorId( gameLocationEditorId( gameLocation ) );
        gameLocationDto.setImageUrl( gameLocation.getImageUrl() );
        gameLocationDto.setId( gameLocation.getId() );
        gameLocationDto.setName( gameLocation.getName() );
        gameLocationDto.setDescription( gameLocation.getDescription() );
        gameLocationDto.setWebsiteUrl( gameLocation.getWebsiteUrl() );
        gameLocationDto.setPromoInfo( gameLocation.getPromoInfo() );
        gameLocationDto.setLatitude( gameLocation.getLatitude() );
        gameLocationDto.setLongitude( gameLocation.getLongitude() );
        gameLocationDto.setVisibilityRadius( gameLocation.getVisibilityRadius() );

        return gameLocationDto;
    }

    @Override
    public GameLocation toEntity(GameLocationDto gameLocationDto) {
        if ( gameLocationDto == null ) {
            return null;
        }

        GameLocation gameLocation = new GameLocation();

        gameLocation.setId( gameLocationDto.getId() );
        gameLocation.setName( gameLocationDto.getName() );
        gameLocation.setDescription( gameLocationDto.getDescription() );
        gameLocation.setWebsiteUrl( gameLocationDto.getWebsiteUrl() );
        gameLocation.setImageUrl( gameLocationDto.getImageUrl() );
        gameLocation.setPromoInfo( gameLocationDto.getPromoInfo() );
        gameLocation.setLatitude( gameLocationDto.getLatitude() );
        gameLocation.setLongitude( gameLocationDto.getLongitude() );
        gameLocation.setVisibilityRadius( gameLocationDto.getVisibilityRadius() );

        return gameLocation;
    }

    private Long gameLocationEditorId(GameLocation gameLocation) {
        if ( gameLocation == null ) {
            return null;
        }
        User editor = gameLocation.getEditor();
        if ( editor == null ) {
            return null;
        }
        Long id = editor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
