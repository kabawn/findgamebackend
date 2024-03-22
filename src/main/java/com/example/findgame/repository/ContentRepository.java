package com.example.findgame.repository;

import com.example.findgame.entity.Balise;
import com.example.findgame.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByBaliseId(Long baliseId);


}