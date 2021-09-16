package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.Objects;

/**
 * Модель для отображения детальной статистики за период
 *
 * @author Мулярчук Александр
 */
public class DetailedStatisticPerPeriod {
    private String type;
    private int countQuestions;
    private int countCorrectQuestions;

    /**
     * Конструктор
     *
     * @param type                  тип вопроса
     * @param countQuestions        количество отвеченных вопросов
     * @param countCorrectQuestions количество правильно отвеченных вопросов
     */
    public DetailedStatisticPerPeriod(String type, int countQuestions, int countCorrectQuestions) {
        this.type = type;
        this.countQuestions = countQuestions;
        this.countCorrectQuestions = countCorrectQuestions;
    }

    public String getType() {
        return type;
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    public int getCountCorrectQuestions() {
        return countCorrectQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailedStatisticPerPeriod that = (DetailedStatisticPerPeriod) o;
        return countQuestions == that.countQuestions
                && countCorrectQuestions == that.countCorrectQuestions
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, countQuestions, countCorrectQuestions);
    }
}
