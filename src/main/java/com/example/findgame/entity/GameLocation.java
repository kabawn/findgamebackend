package com.example.findgame.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "game_locations")
public class GameLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(name = "name",nullable = false)
private String name;

@Column(name = "description" , nullable = false)
private String description;

@Column (name = "website_url", nullable = false)
private String websiteUrl;

@Column(name = "image_url" , nullable = false)
private String imageUrl;



    @Column(name = "promo_info", nullable = false)
private String promoInfo;
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
