package com.example.findgame.service;

import com.example.findgame.entity.UserLocationInteraction;
import com.example.findgame.repository.UserLocationInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLocationInteractionService {

    @Autowired
    private UserLocationInteractionRepository userLocationInteractionRepository;

    public UserLocationInteraction recordInteraction(UserLocationInteraction interaction) {
        return userLocationInteractionRepository.save(interaction);
    }

    // Additional service methods as needed
}
