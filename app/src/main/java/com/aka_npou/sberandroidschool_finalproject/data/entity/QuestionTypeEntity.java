package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_types")
public class QuestionTypeEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String type;

    public QuestionTypeEntity() {
    }

    @Ignore
    public QuestionTypeEntity(long id, String type) {
        this.id = id;
        this.type = type;
    }
}
