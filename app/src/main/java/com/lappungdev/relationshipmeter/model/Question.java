package com.lappungdev.relationshipmeter.model;

public class Question {

    private int id;
    private String question;
    private String pairQuestion;

    public Question() {
        setId(0);
        setQuestion("");
        setPairQuestion("");
    }

    public Question(String question, String pairQuestion) {
        this.setQuestion(question);
        this.setPairQuestion(pairQuestion);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPairQuestion() {
        return pairQuestion;
    }

    public void setPairQuestion(String pairQuestion) {
        this.pairQuestion = pairQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
