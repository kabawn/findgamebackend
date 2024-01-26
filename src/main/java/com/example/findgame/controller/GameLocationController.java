package com.example.findgame.controller;

import com.example.findgame.dto.GameLocationDto;
import com.example.findgame.entity.GameLocation;
import com.example.findgame.service.GameLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-locations")
public class GameLocationController {

    @Autowired
    private GameLocationService gameLocationService;

    @GetMapping
    public List<GameLocation> getAllGameLocations() {
        return gameLocationService.getAllGameLocations();
    }

    @GetMapping("/{id}")
    public GameLocation getGameLocationById(@PathVariable Long id) {
        return gameLocationService.getGameLocationById(id);
    }

    @PostMapping
    public ResponseEntity<GameLocation> addGameLocation(@RequestBody GameLocationDto gameLocationDto) {
        GameLocation gameLocation = gameLocationService.addGameLocation(gameLocationDto);
        return new ResponseEntity<>(gameLocation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public GameLocation updateGameLocation(@PathVariable Long id, @RequestBody GameLocation gameLocation) {
        gameLocation.setId(id);
        return gameLocationService.saveGameLocation(gameLocation);
    }

    @DeleteMapping("/{id}")
    public void deleteGameLocation(@PathVariable Long id) {
        gameLocationService.deleteGameLocation(id);
    }

    @GetMapping("/user/{userId}/discoverable")
    public ResponseEntity<List<GameLocationDto>> getDiscoverableLocationsForUser(@PathVariable Long userId) {
        List<GameLocationDto> discoverableLocations = gameLocationService.getDiscoverableLocationsForUser(userId);
        return ResponseEntity.ok(discoverableLocations);
    }




}

