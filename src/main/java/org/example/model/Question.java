package org.example.model;

public class Question {
    private final String title;
    private final String text;
    private final String description;
    private final int id;
    private final int[] variants;

    public Question(int id, String text, String title,String description, int[] variants) {
        this.title = title;
        this.text = text;
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
