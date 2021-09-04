package com.aka_npou.sberandroidschool_finalproject.data.entity;

public class StatisticEntity {
    public long id;
    public long questionId;
    public int answerIndex;
    public boolean isCorrectAnswer;
    public long dateOfAnswer;

    public StatisticEntity() {
    }

    public StatisticEntity(long id, long questionId, int answerIndex, boolean isCorrectAnswer, long dateOfAnswer) {
        this.id = id;
        this.questionId = questionId;
        this.answerIndex = answerIndex;
        this.isCorrectAnswer = isCorrectAnswer;
        this.dateOfAnswer = dateOfAnswer;
    }
}
