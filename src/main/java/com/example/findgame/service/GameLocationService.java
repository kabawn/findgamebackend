package com.example.findgame.service;

import com.example.findgame.dto.GameLocationDto;
import com.example.findgame.entity.GameLocation;
import com.example.findgame.entity.User;
import com.example.findgame.exception.ResourceNotFoundException;
import com.example.findgame.mapper.GameLocationMapper;
import com.example.findgame.repository.GameLocationRepository;
import com.example.findgame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameLocationService {

    @Autowired
    private GameLocationRepository gameLocationRepository;

    @Autowired
    private UserService userService; // Autowire UserService
    public List<GameLocation> getAllGameLocations() {
        return gameLocationRepository.findAll();
    }

    @Autowired
    private UserRepository userRepository; // If you decide to use this directly

    @Autowired
    private GameLocationMapper gameLocationMapper; // Aut

    public GameLocation getGameLocationById(Long id) {
        return gameLocationRepository.findById(id).orElse(null);
    }

    public GameLocation saveGameLocation(GameLocation gameLocation) {
        return gameLocationRepository.save(gameLocation);
    }

    public void deleteGameLocation(Long id) {
        gameLocationRepository.deleteById(id);
    }

    public GameLocation addGameLocation(GameLocationDto gameLocationDto) {
        GameLocation gameLocation = new GameLocation();
        gameLocation.setLatitude(gameLocationDto.getLatitude());
        gameLocation.setLongitude(gameLocationDto.getLongitude());
        gameLocation.setVisibilityRadius(gameLocationDto.getVisibilityRadius());
        gameLocation.setHtmlContent(gameLocationDto.getHtmlContent());

        // Fetch the editor/user from the UserService based on their ID
        User editor = userService.getUserById(gameLocationDto.getEditorId()).toEntity();
        gameLocation.setEditor(editor);

        return gameLocationRepository.save(gameLocation);
    }

    public List<GameLocationDto> getDiscoverableLocationsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        List<GameLocation> allLocations = gameLocationRepository.findAll();
        return allLocations.stream()
                .filter(location -> {
                    double distance = computeDistance(location.getLatitude(), location.getLongitude(), user.getLatitude(), user.getLongitude());
                    boolean isDiscoverable = distance <= user.getSearchRadius() && distance <= location.getVisibilityRadius();
                    System.out.println("Processing location with ID: " + location.getId());
                    System.out.println("Calculated distance: " + distance);
                    System.out.println("Is discoverable: " + isDiscoverable);
                    return isDiscoverable;
                })
                .map(gameLocationMapper::toDto)
                .collect(Collectors.toList());
    }


    private boolean isLocationDiscoverableForUser(GameLocation location, User user) {
        double distance = computeDistance(location.getLatitude(), location.getLongitude(), user.getLatitude(), user.getLongitude());
        return distance <= user.getSearchRadius() && distance <= location.getVisibilityRadius();
    }

    private double computeDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in kilometers
    }

    // Additional service methods as needed

}
