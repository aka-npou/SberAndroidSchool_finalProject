package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions", foreignKeys = @ForeignKey(entity = QuestionTypeEntity.class, parentColumns = "id", childColumns = "question_type"))
public class QuestionEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String questionText;
    public int correctAnswerIndex;
    @ColumnInfo(name = "question_type")
    public long questionType;

    public QuestionEntity() {
    }

    @Ignore
    public QuestionEntity(long id, String questionText, int correctAnswerIndex) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
