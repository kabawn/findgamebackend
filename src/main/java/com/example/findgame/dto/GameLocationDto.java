package com.example.findgame.dto;

public class GameLocationDto {

    private Long id;



    private Double latitude;
    private Double longitude;
    private String htmlContent;



    private Double visibilityRadius;
    private Long editorId;

    // Getters, setters, and other methods...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
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
