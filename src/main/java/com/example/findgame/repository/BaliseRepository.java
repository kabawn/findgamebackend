package com.example.findgame.repository;

import com.example.findgame.entity.Balise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaliseRepository extends JpaRepository<Balise, Long> {
    List<Balise> findByUserId(Long userId); // Fetch balises added by a specific user

}
