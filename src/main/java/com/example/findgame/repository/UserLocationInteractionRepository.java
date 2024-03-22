package com.example.findgame.repository;

import com.example.findgame.entity.UserLocationInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationInteractionRepository extends JpaRepository<UserLocationInteraction, Long> {
    // Additional query methods if needed
}
