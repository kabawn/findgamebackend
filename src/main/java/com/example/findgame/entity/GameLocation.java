package com.example.findgame.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "game_locations")
public class GameLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "html_content", nullable = false)
    private String htmlContent;



    @Column(name = "visibility_radius", nullable = false)
    private Double visibilityRadius;


    @ManyToOne
    @JoinColumn(name = "editor_id")
    private User editor;

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



    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }
    public Double getVisibilityRadius() {
        return visibilityRadius;
    }

    public void setVisibilityRadius(Double visibilityRadius) {
        this.visibilityRadius = visibilityRadius;
    }
}
