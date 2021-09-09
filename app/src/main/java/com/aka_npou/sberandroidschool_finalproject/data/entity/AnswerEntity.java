package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "answers")
public class AnswerEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String text;
    @ColumnInfo(name = "question_id")
    public long questionId;

    public AnswerEntity() {
    }
}
