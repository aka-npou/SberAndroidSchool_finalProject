package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "questions")
public class QuestionEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String questionText;
    public int correctAnswerIndex;

    public QuestionEntity() {
    }

    @Ignore
    public QuestionEntity(long id, String questionText, int correctAnswerIndex) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
