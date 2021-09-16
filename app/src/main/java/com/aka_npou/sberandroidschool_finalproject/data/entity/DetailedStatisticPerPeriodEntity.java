package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Ignore;

import java.util.Objects;

/**
 * Модель для хранения в базе данных детальной статистики за период
 *
 * @author Мулярчук Александр
 */
public class DetailedStatisticPerPeriodEntity {
    public String type;
    public int countQuestions;
    public int countCorrectQuestions;

    public DetailedStatisticPerPeriodEntity() {
    }

    @Ignore
    public DetailedStatisticPerPeriodEntity(String type, int countQuestions, int countCorrectQuestions) {
        this.type = type;
        this.countQuestions = countQuestions;
        this.countCorrectQuestions = countCorrectQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailedStatisticPerPeriodEntity that = (DetailedStatisticPerPeriodEntity) o;
        return countQuestions == that.countQuestions
                && countCorrectQuestions == that.countCorrectQuestions
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, countQuestions, countCorrectQuestions);
    }
}
