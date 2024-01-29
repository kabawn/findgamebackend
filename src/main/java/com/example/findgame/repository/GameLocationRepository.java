package com.example.findgame.repository;

import com.example.findgame.entity.GameLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameLocationRepository extends JpaRepository<GameLocation, Long> {
    // Method to find locations by editor's ID
    List<GameLocation> findByEditorId(Long editorId);

}
