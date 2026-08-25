package org.example.model;

import java.util.List;

public class Question {
    private final String title;
    private final String text;
    private final String description;
    private final int id;
    private int[] variants;

    public Question(int id, String text, String title,String description, int[] variants) {
        this.text = text;
        this.title = title;
        this.description = description;
        this.id = id;
        this.variants = variants;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDescription() {
        return description;
    }

    public int[] getVariants() {
        return variants;
    }

}
