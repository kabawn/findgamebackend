package com.example.findgame.controller;

import com.example.findgame.dto.BaliseDTO;
import com.example.findgame.dto.BaliseWithContentsDTO;
import com.example.findgame.service.BaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balises")
public class BaliseController {
    @Autowired
    private final BaliseService baliseService;

    public BaliseController(BaliseService baliseService) {
        this.baliseService = baliseService;
    }

    @PostMapping
    public ResponseEntity<BaliseDTO> createBalise(@RequestBody BaliseDTO baliseDTO) {
        return ResponseEntity.ok(baliseService.createBalise(baliseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaliseDTO> getBaliseById(@PathVariable Long id) {
        return ResponseEntity.ok(baliseService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BaliseDTO>> getBalisesByUserId(@PathVariable Long userId) {
        List<BaliseDTO> balises = baliseService.findByUserId(userId);
        return ResponseEntity.ok(balises);
    }

    @GetMapping
    public ResponseEntity<List<BaliseDTO>> getAllBalises() {
        return ResponseEntity.ok(baliseService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaliseDTO> updateBalise(@PathVariable Long id, @RequestBody BaliseDTO baliseDTO) {
        baliseDTO.setId(id); // Ensure the DTO has the correct ID
        return ResponseEntity.ok(baliseService.updateBalise(baliseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBalise(@PathVariable Long id) {
        baliseService.deleteBalise(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/users/{userId}/balises-with-contents")
    public ResponseEntity<List<BaliseWithContentsDTO>> getBalisesWithContents(@PathVariable Long userId) {
        List<BaliseWithContentsDTO> balisesWithContents = baliseService.getBalisesWithContentsByUserId(userId);
        return ResponseEntity.ok(balisesWithContents);
    }

}
