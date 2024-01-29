package com.example.findgame.dto;

public class GameLocationDto {

    private Long id;



    private String name;
    private String description;
    private String websiteUrl;
    private String promoInfo;

    private Double latitude;
    private Double longitude;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl;


    private Double visibilityRadius;
    private Long editorId;

    // Getters, setters, and other methods...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }



    public String getPromoInfo() {
        return promoInfo;
    }

    public void setPromoInfo(String promoInfo) {
        this.promoInfo = promoInfo;
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


    public Long getEditorId() {
        return editorId;
    }

    public void setEditorId(Long editorId) {
        this.editorId = editorId;
    }
    public Double getVisibilityRadius() {
        return visibilityRadius;
    }

    public void setVisibilityRadius(Double visibilityRadius) {
        this.visibilityRadius = visibilityRadius;
    }
}
