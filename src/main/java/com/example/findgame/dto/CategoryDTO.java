package com.example.findgame.dto;

public class CategoryDTO {

    private Long id;
    private String name;
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


    // Optionally include related contents if needed, considering implications on JSON size and circular references

    // Getters and Setters
}
