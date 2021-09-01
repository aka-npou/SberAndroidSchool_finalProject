package com.aka_npou.sberandroidschool_finalproject.data.entity;

import java.util.List;

public class QuestionEntity {
    public long id;
    public String questionText;
    public List<String> answers;
    public int correctAnswerIndex;

    public QuestionEntity() {
    }

    public QuestionEntity(long id, String questionText, List<String> answers, int correctAnswerIndex) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
