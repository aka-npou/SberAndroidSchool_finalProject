package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.Date;

public class Statistic {
    private final long id;
    private final long questionId;
    private final int answerIndex;
    private final boolean isCorrectAnswer;
    private final Date dateOfAnswer;

    public Statistic(long id, long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        this.id = id;
        this.questionId = questionId;
        this.answerIndex = answerIndex;
        this.isCorrectAnswer = isCorrectAnswer;
        this.dateOfAnswer = dateOfAnswer;
    }

    public long getId() {
        return id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public Date getDateOfAnswer() {
        return dateOfAnswer;
    }
}
