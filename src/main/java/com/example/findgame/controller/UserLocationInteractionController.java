package com.example.findgame.controller;

import com.example.findgame.entity.UserLocationInteraction;
import com.example.findgame.service.UserLocationInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-interactions")
public class UserLocationInteractionController {

    @Autowired
    private UserLocationInteractionService userLocationInteractionService;

    @PostMapping
    public UserLocationInteraction recordInteraction(@RequestBody UserLocationInteraction interaction) {
        return userLocationInteractionService.recordInteraction(interaction);
    }

    // Additional endpoints as needed
}
