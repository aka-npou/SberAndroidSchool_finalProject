package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.List;

public class Question {
    private final long id;
    private final String questionText;
    private final List<String> answers;
    private final int correctAnswerIndex;
    private final String type;

    public Question(long id, String questionText, List<String> answers, int correctAnswerIndex, String type) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.type = type;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
