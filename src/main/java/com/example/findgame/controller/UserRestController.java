package com.example.findgame.controller;

import com.example.findgame.dto.LoginRequest;
import com.example.findgame.dto.UserDto;
import com.example.findgame.dto.UserLocationInteractionDto;
import com.example.findgame.entity.User;
import com.example.findgame.security.JwtResponse;
import com.example.findgame.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto registerUser(@Valid @RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @PostMapping("/login")
    public JwtResponse loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody UserDto updatedUserDto) {
        return userService.updateUser(userId, updatedUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
        return new ResponseEntity<>("Invalid email or password.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @PostMapping("/users/{userId}/location")
    public ResponseEntity<UserDto> updateUserLocationAndRadius(
            @PathVariable Long userId,
            @RequestBody Map<String, Double> locationDetails) {

        Double latitude = locationDetails.get("latitude");
        Double longitude = locationDetails.get("longitude");
        Double searchRadius = locationDetails.get("searchRadius");

        UserDto updatedUser = userService.updateUserLocationAndRadius(userId, latitude, longitude, searchRadius);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<User> updateUserLocation(@PathVariable Long id, @RequestBody UserLocationInteractionDto locationDto) {
        User updatedUser = userService.updateUserLocation(id, locationDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/{userId}/search-radius")
    public ResponseEntity<UserDto> updateUserSearchRadius(@PathVariable Long userId, @RequestBody Double searchRadius) {
        try {
            UserDto updatedUser = userService.updateUserSearchRadius(userId, searchRadius);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // or any other appropriate status code
        }
    }



}

