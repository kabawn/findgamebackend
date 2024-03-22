package com.example.findgame.dto;

import java.util.Map;

public class ContentDTO {
    private Long id;
    private Long categoryId;
    private Long baliseId;
    private String title;
    private String body;
    private String imageUrl; // For the image URL or filename

    private Map<String, String> properties; // Dynamic fields based on category
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBaliseId() {
        return baliseId;
    }

    public void setBaliseId(Long baliseId) {
        this.baliseId = baliseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Getters and setters...
}
