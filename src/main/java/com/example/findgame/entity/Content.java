package com.example.findgame.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Category category;

    @ManyToOne
    private Balise balise;

    private String title;

    @Lob
    private String body;
    private String imageUrl; // Field to store image URL or filename

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "content_properties", joinColumns = @JoinColumn(name = "content_id"))
    @MapKeyColumn(name = "property_key") // Renamed from 'key' to 'property_key'
    @Column(name = "property_value") // Renamed from 'value' to 'property_value'
    private Map<String, String> properties = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Balise getBalise() {
        return balise;
    }

    public void setBalise(Balise balise) {
        this.balise = balise;
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

    // Standard getters and setters...

    // Getters and Setters


}
