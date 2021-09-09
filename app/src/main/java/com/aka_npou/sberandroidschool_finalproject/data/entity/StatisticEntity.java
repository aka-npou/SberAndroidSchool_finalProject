package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "statistics")
public class StatisticEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long questionId;
    public int answerIndex;
    public boolean isCorrectAnswer;
    public long dateOfAnswer;

    public StatisticEntity() {
    }

    @Ignore
    public StatisticEntity(long id, long questionId, int answerIndex, boolean isCorrectAnswer, long dateOfAnswer) {
        this.id = id;
        this.questionId = questionId;
        this.answerIndex = answerIndex;
        this.isCorrectAnswer = isCorrectAnswer;
        this.dateOfAnswer = dateOfAnswer;
    }
}
