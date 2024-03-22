package com.example.findgame.mapper;

import com.example.findgame.dto.ContentDTO;
import com.example.findgame.entity.Balise;
import com.example.findgame.entity.Category;
import com.example.findgame.entity.Content;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-19T10:14:08+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class ContentMapperImpl implements ContentMapper {

    @Override
    public ContentDTO toDto(Content content) {
        if ( content == null ) {
            return null;
        }

        ContentDTO contentDTO = new ContentDTO();

        contentDTO.setCategoryId( contentCategoryId( content ) );
        contentDTO.setBaliseId( contentBaliseId( content ) );
        contentDTO.setImageUrl( content.getImageUrl() );
        contentDTO.setId( content.getId() );
        contentDTO.setTitle( content.getTitle() );
        contentDTO.setBody( content.getBody() );
        Map<String, String> map = content.getProperties();
        if ( map != null ) {
            contentDTO.setProperties( new LinkedHashMap<String, String>( map ) );
        }

        return contentDTO;
    }

    @Override
    public Content toEntity(ContentDTO contentDTO) {
        if ( contentDTO == null ) {
            return null;
        }

        Content content = new Content();

        content.setId( contentDTO.getId() );
        content.setTitle( contentDTO.getTitle() );
        content.setBody( contentDTO.getBody() );
        Map<String, String> map = contentDTO.getProperties();
        if ( map != null ) {
            content.setProperties( new LinkedHashMap<String, String>( map ) );
        }
        content.setImageUrl( contentDTO.getImageUrl() );

        return content;
    }

    private Long contentCategoryId(Content content) {
        if ( content == null ) {
            return null;
        }
        Category category = content.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long contentBaliseId(Content content) {
        if ( content == null ) {
            return null;
        }
        Balise balise = content.getBalise();
        if ( balise == null ) {
            return null;
        }
        Long id = balise.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
