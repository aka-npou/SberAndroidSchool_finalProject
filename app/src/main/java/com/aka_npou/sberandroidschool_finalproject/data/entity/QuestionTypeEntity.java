package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_types")
public class QuestionTypeEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String type;

    public QuestionTypeEntity() {
    }
}
