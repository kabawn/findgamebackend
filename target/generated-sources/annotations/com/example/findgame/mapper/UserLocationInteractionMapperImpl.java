package com.example.findgame.mapper;

import com.example.findgame.dto.UserLocationInteractionDto;
import com.example.findgame.entity.GameLocation;
import com.example.findgame.entity.User;
import com.example.findgame.entity.UserLocationInteraction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T13:16:27+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserLocationInteractionMapperImpl implements UserLocationInteractionMapper {

    @Override
    public UserLocationInteractionDto toDto(UserLocationInteraction interaction) {
        if ( interaction == null ) {
            return null;
        }

        UserLocationInteractionDto userLocationInteractionDto = new UserLocationInteractionDto();

        userLocationInteractionDto.setUserId( interactionUserId( interaction ) );
        userLocationInteractionDto.setGameLocationId( interactionGameLocationId( interaction ) );
        userLocationInteractionDto.setId( interaction.getId() );
        userLocationInteractionDto.setInteractionTimestamp( interaction.getInteractionTimestamp() );

        return userLocationInteractionDto;
    }

    @Override
    public UserLocationInteraction toEntity(UserLocationInteractionDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserLocationInteraction userLocationInteraction = new UserLocationInteraction();

        userLocationInteraction.setUser( userLocationInteractionDtoToUser( dto ) );
        userLocationInteraction.setGameLocation( userLocationInteractionDtoToGameLocation( dto ) );
        userLocationInteraction.setId( dto.getId() );
        userLocationInteraction.setInteractionTimestamp( dto.getInteractionTimestamp() );

        return userLocationInteraction;
    }

    private Long interactionUserId(UserLocationInteraction userLocationInteraction) {
        if ( userLocationInteraction == null ) {
            return null;
        }
        User user = userLocationInteraction.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long interactionGameLocationId(UserLocationInteraction userLocationInteraction) {
        if ( userLocationInteraction == null ) {
            return null;
        }
        GameLocation gameLocation = userLocationInteraction.getGameLocation();
        if ( gameLocation == null ) {
            return null;
        }
        Long id = gameLocation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User userLocationInteractionDtoToUser(UserLocationInteractionDto userLocationInteractionDto) {
        if ( userLocationInteractionDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userLocationInteractionDto.getUserId() );

        return user;
    }

    protected GameLocation userLocationInteractionDtoToGameLocation(UserLocationInteractionDto userLocationInteractionDto) {
        if ( userLocationInteractionDto == null ) {
            return null;
        }

        GameLocation gameLocation = new GameLocation();

        gameLocation.setId( userLocationInteractionDto.getGameLocationId() );

        return gameLocation;
    }
}
