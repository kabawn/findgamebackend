package com.example.findgame.dto;

import java.util.List;

public class BaliseWithContentsDTO {
    private BaliseDTO balise;
    private List<ContentDTO> contents;

    // Ensure you have a constructor, getters, and setters

    public BaliseWithContentsDTO(BaliseDTO balise, List<ContentDTO> contents) {
        this.balise = balise;
        this.contents = contents;
    }

    public BaliseDTO getBalise() {
        return balise;
    }

    public void setBalise(BaliseDTO balise) {
        this.balise = balise;
    }

    public List<ContentDTO> getContents() {
        return contents;
    }

    public void setContents(List<ContentDTO> contents) {
        this.contents = contents;
    }

    // Getters and setters
}
