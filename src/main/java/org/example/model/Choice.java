package org.example.model;

public class Choice {
    private String title;
    private int questionId;

    public Choice(int questionId, String title) {
        this.questionId = questionId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
