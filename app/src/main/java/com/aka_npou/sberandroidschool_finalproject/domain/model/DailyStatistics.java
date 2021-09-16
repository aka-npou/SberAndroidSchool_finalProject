package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.Date;
import java.util.Objects;

/**
 * Модель для отображения пользователю статистики
 *
 * @author Мулярчук Александр
 */
public class DailyStatistics {
    private final Date dateOfAnswer;
    private int countQuestions;
    private int countCorrectQuestions;

    /**
     * Конструктор
     *
     * @param mDateOfAnswer дата ответа на вопрос
     */
    public DailyStatistics(Date mDateOfAnswer) {
        this.dateOfAnswer = mDateOfAnswer;
        countQuestions = 0;
        countCorrectQuestions = 0;
    }

    /**
     * Конструктор
     *
     * @param dateOfAnswer          дата ответа на вопрос
     * @param countQuestions        количество отвеченных вопросов
     * @param countCorrectQuestions количество правильно отвеченных вопросов
     */
    public DailyStatistics(Date dateOfAnswer, int countQuestions, int countCorrectQuestions) {
        this.dateOfAnswer = dateOfAnswer;
        this.countQuestions = countQuestions;
        this.countCorrectQuestions = countCorrectQuestions;
    }

    public Date getDateOfAnswer() {
        return dateOfAnswer;
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    public int getCountCorrectQuestions() {
        return countCorrectQuestions;
    }

    public void setCountQuestions(int countQuestions) {
        this.countQuestions = countQuestions;
    }

    public void setCountCorrectQuestions(int countCorrectQuestions) {
        this.countCorrectQuestions = countCorrectQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyStatistics that = (DailyStatistics) o;
        return countQuestions == that.countQuestions
                && countCorrectQuestions == that.countCorrectQuestions
                && Objects.equals(dateOfAnswer, that.dateOfAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfAnswer, countQuestions, countCorrectQuestions);
    }
}
