package com.imam.myasi;

public class Question {
    private String question;
    private String category;
    private String id;

    public Question(){}

    public Question(String question, String category, String id) {
        this.question = question;
        this.category = category;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
