package com.example.findgame.dto;

import java.time.LocalDateTime;

public class UserLocationInteractionDto {

    private Long id;
    private Long userId;
    private Long gameLocationId;
    private LocalDateTime interactionTimestamp;
    private Double latitude;
    private Double longitude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameLocationId() {
        return gameLocationId;
    }

    public void setGameLocationId(Long gameLocationId) {
        this.gameLocationId = gameLocationId;
    }

    public LocalDateTime getInteractionTimestamp() {
        return interactionTimestamp;
    }

    public void setInteractionTimestamp(LocalDateTime interactionTimestamp) {
        this.interactionTimestamp = interactionTimestamp;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
