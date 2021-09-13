package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Модель для хранения в базе данных ответа
 *
 * @author Мулярчук Александр
 */
@Entity(tableName = "answers")
public class AnswerEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String text;
    @ColumnInfo(name = "question_id")
    public long questionId;

    public AnswerEntity() {
    }

    public AnswerEntity(long id, String text, long questionId) {
        this.id = id;
        this.text = text;
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerEntity that = (AnswerEntity) o;
        return id == that.id && questionId == that.questionId && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, questionId);
    }
}
