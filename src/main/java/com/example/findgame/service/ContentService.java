package com.example.findgame.service;

import com.example.findgame.dto.ContentDTO;
import com.example.findgame.entity.Content;
import com.example.findgame.mapper.ContentMapper;
import com.example.findgame.repository.BaliseRepository;
import com.example.findgame.repository.CategoryRepository;
import com.example.findgame.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final BaliseRepository baliseRepository;
    private final ContentMapper contentMapper;

    @Autowired
    public ContentService(ContentRepository contentRepository, CategoryRepository categoryRepository,
                          BaliseRepository baliseRepository, ContentMapper contentMapper) {
        this.contentRepository = contentRepository;
        this.categoryRepository = categoryRepository;
        this.baliseRepository = baliseRepository;
        this.contentMapper = contentMapper;
    }

    public ContentDTO createContent(ContentDTO contentDTO) {
        Content content = contentMapper.toEntity(contentDTO);

        // Fetch and set Category and Balise based on IDs
        if (contentDTO.getCategoryId() != null) {
            categoryRepository.findById(contentDTO.getCategoryId()).ifPresent(content::setCategory);
        }
        if (contentDTO.getBaliseId() != null) {
            baliseRepository.findById(contentDTO.getBaliseId()).ifPresent(content::setBalise);
        }

        // Here, directly set the imageUrl from the DTO to the Content entity
        content.setImageUrl(contentDTO.getImageUrl());

        content = contentRepository.save(content);
        return contentMapper.toDto(content);
    }

    // Additional methods as needed...
}

