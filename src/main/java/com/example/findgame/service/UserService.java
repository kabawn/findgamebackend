package com.example.findgame.service;

import com.example.findgame.dto.LoginRequest;
import com.example.findgame.dto.UserDto;
import com.example.findgame.dto.UserLocationInteractionDto;
import com.example.findgame.emailService.EmailService;
import com.example.findgame.entity.User;
import com.example.findgame.exception.ResourceNotFoundException;
import com.example.findgame.mapper.UserMapper;
import com.example.findgame.repository.UserRepository;
import com.example.findgame.security.JwtResponse;
import com.example.findgame.security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService customUserDetailsService;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        User user = userRepository.findByEmail(userDetails.getUsername());
        if (!user.isEnabled()) {
            throw new BadCredentialsException("Please confirm your email address");
        }




        String jwt = jwtUtil.generateJwtToken(userDetails);

        // Ensure jwt is not null
        if (jwt == null) {
            throw new RuntimeException("JWT token generation failed.");
        }

        // Return the JWT response with additional user data
        return new JwtResponse(jwt, user.getRole(), user.getId(), user.getEmail());
    }



    public UserDto registerUser(UserDto userDto) {
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser != null) {
            throw new IllegalArgumentException("Email is already in use");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = userMapper.toEntity(userDto);
        user.setEnabled(false);
        String verificationCode = UUID.randomUUID().toString();
        user.setVerificationCode(verificationCode);

        // Set the default search radius if it's not provided during registration
        if (user.getSearchRadius() == null) {
            user.setSearchRadius(1.0); //  1 km
        }

        User savedUser = userRepository.save(user);

        emailService.sendVerificationEmail(savedUser); // Updated this line to pass the User object

        return userMapper.toDto(savedUser);
    }



    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        existingUser.setFirstName(updatedUserDto.getFirstName());
        existingUser.setLastName(updatedUserDto.getLastName());
        existingUser.setEmail(updatedUserDto.getEmail());
        existingUser.setPassword(updatedUserDto.getPassword());
        existingUser.setRole(updatedUserDto.getRole());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(existingUser);
    }

    public void confirmEmail(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            throw new IllegalArgumentException("Invalid verification code");
        }

        user.setEnabled(true);
        user.setVerificationCode(null);
        userRepository.save(user);
    }


    public UserDto updateUserLocationAndRadius(Long userId, Double latitude, Double longitude, Double searchRadius) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        user.setSearchRadius(searchRadius);

        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }
    public User updateUserLocation(Long id, UserLocationInteractionDto locationDto) {
        // Find the user by ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        // Update the user's latitude and longitude with values from locationDto
        user.setLatitude(locationDto.getLatitude());
        user.setLongitude(locationDto.getLongitude());

        // Save the user and return
        return userRepository.save(user);
    }

    private static final Double MIN_RADIUS = 1.0;  // Example min value
    private static final Double MAX_RADIUS = 10.0; // Example max value

    public UserDto updateUserSearchRadius(Long userId, Double searchRadius) {
        if (searchRadius < MIN_RADIUS || searchRadius > MAX_RADIUS) {
            throw new IllegalArgumentException("Search radius must be between " + MIN_RADIUS + " and " + MAX_RADIUS);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setSearchRadius(searchRadius);
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

}
