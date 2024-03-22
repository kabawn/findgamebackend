package com.example.findgame.service;

import com.example.findgame.dto.BaliseDTO;
import com.example.findgame.dto.BaliseWithContentsDTO;
import com.example.findgame.dto.ContentDTO;
import com.example.findgame.entity.Balise;
import com.example.findgame.entity.Content;
import com.example.findgame.mapper.BaliseMapper;
import com.example.findgame.mapper.ContentMapper;
import com.example.findgame.repository.BaliseRepository;
import com.example.findgame.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaliseService {
    @Autowired
    private BaliseMapper baliseMapper;

    @Autowired
    private ContentMapper contentMapper;
    private final BaliseRepository baliseRepository;
    @Autowired
    private ContentRepository contentRepository;


    // Constructor injection is being used, so @Autowired is not necessary here
    public BaliseService(BaliseRepository baliseRepository) {
        this.baliseRepository = baliseRepository;
    }

    public BaliseDTO createBalise(BaliseDTO baliseDTO) {
        Balise balise = BaliseMapper.INSTANCE.baliseDTOToBalise(baliseDTO);
        // Assume handling of the User association is done within baliseDTOToBalise method or elsewhere as needed
        balise = baliseRepository.save(balise);
        return BaliseMapper.INSTANCE.baliseToBaliseDTO(balise);
    }

    // Example method for findById
    public BaliseDTO findById(Long id) {
        return baliseRepository.findById(id)
                .map(BaliseMapper.INSTANCE::baliseToBaliseDTO)
                .orElseThrow(() -> new RuntimeException("Balise not found")); // Consider using a more specific exception
    }

    public List<BaliseDTO> findByUserId(Long userId) {
        return baliseRepository.findByUserId(userId).stream()
                .map(BaliseMapper.INSTANCE::baliseToBaliseDTO)
                .collect(Collectors.toList());
    }

    // Example method for findAll
    public List<BaliseDTO> findAll() {
        return baliseRepository.findAll().stream()
                .map(BaliseMapper.INSTANCE::baliseToBaliseDTO)
                .collect(Collectors.toList());
    }

    // Example method for update - assumes BaliseDTO contains an id
    public BaliseDTO updateBalise(BaliseDTO baliseDTO) {
        if (baliseDTO.getId() == null || !baliseRepository.existsById(baliseDTO.getId())) {
            throw new RuntimeException("Balise not found"); // Consider using a more specific exception
        }
        Balise updatedBalise = baliseRepository.save(BaliseMapper.INSTANCE.baliseDTOToBalise(baliseDTO));
        return BaliseMapper.INSTANCE.baliseToBaliseDTO(updatedBalise);
    }

    // Example method for delete
    public void deleteBalise(Long id) {
        if (!baliseRepository.existsById(id)) {
            throw new RuntimeException("Balise not found"); // Consider using a more specific exception
        }
        baliseRepository.deleteById(id);
    }

    // Convert entities to DTOs, assuming you have methods for these conversions
    // In BaliseService.java
    public List<BaliseWithContentsDTO> getBalisesWithContentsByUserId(Long userId) {
        List<Balise> balises = baliseRepository.findByUserId(userId);
        return balises.stream().map(balise -> {
            // Utilize the BaliseMapper to convert Balise to BaliseDTO
            BaliseDTO baliseDTO = baliseMapper.baliseToBaliseDTO(balise);
            // Fetch and convert associated Content to ContentDTO
            List<ContentDTO> contentDTOs = contentRepository.findByBaliseId(balise.getId()).stream()
                    .map(content -> contentMapper.toDto(content)) // Utilize the ContentMapper
                    .collect(Collectors.toList());
            return new BaliseWithContentsDTO(baliseDTO, contentDTOs);
        }).collect(Collectors.toList());
    }

}
