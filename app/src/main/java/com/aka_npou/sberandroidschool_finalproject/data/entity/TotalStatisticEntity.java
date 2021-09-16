package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Ignore;

import java.util.Objects;

/**
 * Модель для хранения в базе данных общей статистики
 *
 * @author Мулярчук Александр
 */
public class TotalStatisticEntity {
    public int countQuestions;
    public int countCorrectAnswers;
    public long firstDay;

    public TotalStatisticEntity() {
    }

    @Ignore
    public TotalStatisticEntity(int countQuestions, int countCorrectAnswers, int firstDay) {
        this.countQuestions = countQuestions;
        this.countCorrectAnswers = countCorrectAnswers;
        this.firstDay = firstDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalStatisticEntity entity = (TotalStatisticEntity) o;
        return countQuestions == entity.countQuestions && countCorrectAnswers == entity.countCorrectAnswers && firstDay == entity.firstDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countQuestions, countCorrectAnswers, firstDay);
    }
}
