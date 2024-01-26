package com.example.findgame.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_location_interactions")
public class UserLocationInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_location_id", nullable = false)
    private GameLocation gameLocation;

    @Column(name = "interaction_timestamp", nullable = false)
    private LocalDateTime interactionTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameLocation getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(GameLocation gameLocation) {
        this.gameLocation = gameLocation;
    }

    public LocalDateTime getInteractionTimestamp() {
        return interactionTimestamp;
    }

    public void setInteractionTimestamp(LocalDateTime interactionTimestamp) {
        this.interactionTimestamp = interactionTimestamp;
    }
}
