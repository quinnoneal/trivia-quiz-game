package com.kenzie.app.gamedto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GameDTO {

    @JsonProperty("clues")
    private List<Clues> clues;

    public List<Clues> getClues() {
        return clues;
    }

    public void setClues(List<Clues> clues) {
        this.clues = clues;
    }
}
