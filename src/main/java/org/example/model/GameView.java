package org.example.model;

import java.util.List;

public class GameView {
    private String title_text;
    private String h1_text;
    private String subtitle_text;
    private String maintext;
    private String description;
    private List<Choice> choices;
    private String error_text;

    public String getTitle_text() {
        return title_text;
    }

    public void setTitle_text(String title_text) {
        this.title_text = title_text;
    }

    public String getH1_text() {
        return h1_text;
    }

    public void setH1_text(String h1_text) {
        this.h1_text = h1_text;
    }

    public String getSubtitle_text() {
        return subtitle_text;
    }

    public void setSubtitle_text(String subtitle_text) {
        this.subtitle_text = subtitle_text;
    }

    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }
}
