package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Модель для хранения в базе статистики по ответам
 *
 * @author Мулярчук Александр
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticEntity entity = (StatisticEntity) o;
        return id == entity.id && questionId == entity.questionId && answerIndex == entity.answerIndex && isCorrectAnswer == entity.isCorrectAnswer && dateOfAnswer == entity.dateOfAnswer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, answerIndex, isCorrectAnswer, dateOfAnswer);
    }

    @Override
    public String toString() {
        return "StatisticEntity{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answerIndex=" + answerIndex +
                ", isCorrectAnswer=" + isCorrectAnswer +
                ", dateOfAnswer=" + dateOfAnswer +
                '}';
    }
}
