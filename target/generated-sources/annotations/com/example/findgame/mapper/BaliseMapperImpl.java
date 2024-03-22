package com.example.findgame.mapper;

import com.example.findgame.dto.BaliseDTO;
import com.example.findgame.entity.Balise;
import com.example.findgame.entity.BaliseType;
import com.example.findgame.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T15:59:02+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class BaliseMapperImpl implements BaliseMapper {

    @Override
    public BaliseDTO baliseToBaliseDTO(Balise balise) {
        if ( balise == null ) {
            return null;
        }

        BaliseDTO baliseDTO = new BaliseDTO();

        baliseDTO.setUserId( baliseUserId( balise ) );
        baliseDTO.setSearchRadius( balise.getSearchRadius() );
        baliseDTO.setName( balise.getName() );
        baliseDTO.setId( balise.getId() );
        if ( balise.getType() != null ) {
            baliseDTO.setType( balise.getType().name() );
        }
        baliseDTO.setLatitude( balise.getLatitude() );
        baliseDTO.setLongitude( balise.getLongitude() );

        return baliseDTO;
    }

    @Override
    public Balise baliseDTOToBalise(BaliseDTO baliseDTO) {
        if ( baliseDTO == null ) {
            return null;
        }

        Balise balise = new Balise();

        balise.setUser( baliseDTOToUser( baliseDTO ) );
        balise.setSearchRadius( baliseDTO.getSearchRadius() );
        balise.setName( baliseDTO.getName() );
        balise.setId( baliseDTO.getId() );
        if ( baliseDTO.getType() != null ) {
            balise.setType( Enum.valueOf( BaliseType.class, baliseDTO.getType() ) );
        }
        balise.setLatitude( baliseDTO.getLatitude() );
        balise.setLongitude( baliseDTO.getLongitude() );

        return balise;
    }

    private Long baliseUserId(Balise balise) {
        if ( balise == null ) {
            return null;
        }
        User user = balise.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User baliseDTOToUser(BaliseDTO baliseDTO) {
        if ( baliseDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( baliseDTO.getUserId() );

        return user;
    }
}
