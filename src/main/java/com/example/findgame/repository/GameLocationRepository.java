package com.example.findgame.repository;

import com.example.findgame.entity.GameLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLocationRepository extends JpaRepository<GameLocation, Long> {
    // Additional query methods if needed
}
