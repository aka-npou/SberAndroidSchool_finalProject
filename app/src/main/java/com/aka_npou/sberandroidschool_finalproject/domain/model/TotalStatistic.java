package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.Objects;

/**
 * Модель для отобращение общей статистики
 *
 * @author Мулярчук Александр
 */
public class TotalStatistic {
    private int countQuestions;
    private int countCorrectAnswers;
    private int daysCount;

    public TotalStatistic(int countQuestions, int countCorrectAnswers, int daysCount) {
        this.countQuestions = countQuestions;
        this.countCorrectAnswers = countCorrectAnswers;
        this.daysCount = daysCount;
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    public int getCountCorrectAnswers() {
        return countCorrectAnswers;
    }

    public int getDaysCount() {
        return daysCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalStatistic that = (TotalStatistic) o;
        return countQuestions == that.countQuestions && countCorrectAnswers == that.countCorrectAnswers && daysCount == that.daysCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countQuestions, countCorrectAnswers, daysCount);
    }
}
