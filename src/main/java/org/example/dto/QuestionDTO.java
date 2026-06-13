package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("variants")
    private int[] variants;

    public QuestionDTO(){}

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int[] getVariants() {
        return variants;
    }
}
