package com.example.findgame.controller;

import com.example.findgame.dto.GameLocationDto;
import com.example.findgame.entity.GameLocation;
import com.example.findgame.service.GameLocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/game-locations")
public class GameLocationController {
    private static final Logger log = LoggerFactory.getLogger(GameLocationController.class);

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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> addGameLocation(
            @RequestPart("gameLocation") String gameLocationJson,
            @RequestPart("file") MultipartFile file) {
        try {
            GameLocationDto gameLocationDto = objectMapper.readValue(gameLocationJson, GameLocationDto.class);
            log.info("Received game location: {}", gameLocationDto);
            log.info("Received file: {}", file.getOriginalFilename());

            // Here, add your logic to handle the DTO and file...
            GameLocation gameLocation = gameLocationService.addGameLocation(gameLocationDto, file);

            return new ResponseEntity<>(gameLocation, HttpStatus.CREATED);
        } catch (IOException e) {
            log.error("Error parsing game location JSON", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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



    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileOnly(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
        }
        log.info("Received file: {}", file.getOriginalFilename());
        // Add logic to store the file if necessary

        return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
    }



    // New endpoint to get game locations by editor's ID
    @GetMapping("/editor/{editorId}")
    public ResponseEntity<List<GameLocation>> getGameLocationsByEditorId(@PathVariable Long editorId) {
        try {
            List<GameLocation> locations = gameLocationService.getGameLocationsByEditorId(editorId);
            return ResponseEntity.ok(locations);
        } catch (Exception e) {
            log.error("Error fetching game locations by editor ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}




