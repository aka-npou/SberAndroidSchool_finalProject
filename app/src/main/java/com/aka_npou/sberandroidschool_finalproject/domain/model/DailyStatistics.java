package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.Date;
import java.util.Objects;

/**
 * Модель для отображения пользователю статистики
 *
 * @author Мулярчук Александр
 */
public class DailyStatistics {
    private final Date mDateOfAnswer;
    private int mCountQuestions;
    private int mCountCorrectQuestions;

    /**
     * Конструктор
     * @param mDateOfAnswer дата ответа на вопрос
     */
    public DailyStatistics(Date mDateOfAnswer) {
        this.mDateOfAnswer = mDateOfAnswer;
        mCountQuestions = 0;
        mCountCorrectQuestions = 0;
    }

    /**
     * Конструктор
     * @param dateOfAnswer дата ответа на вопрос
     * @param countQuestions количество отвеченных вопросов
     * @param countCorrectQuestions количество правильно отвеченных вопросов
     */
    public DailyStatistics(Date dateOfAnswer, int countQuestions, int countCorrectQuestions) {
        mDateOfAnswer = dateOfAnswer;
        mCountQuestions = countQuestions;
        mCountCorrectQuestions = countCorrectQuestions;
    }

    public Date getDateOfAnswer() {
        return mDateOfAnswer;
    }

    public int getCountQuestions() {
        return mCountQuestions;
    }

    public int getCountCorrectQuestions() {
        return mCountCorrectQuestions;
    }

    public void setCountQuestions(int countQuestions) {
        mCountQuestions = countQuestions;
    }

    public void setCountCorrectQuestions(int countCorrectQuestions) {
        mCountCorrectQuestions = countCorrectQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyStatistics that = (DailyStatistics) o;
        return mCountQuestions == that.mCountQuestions && mCountCorrectQuestions == that.mCountCorrectQuestions && Objects.equals(mDateOfAnswer, that.mDateOfAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDateOfAnswer, mCountQuestions, mCountCorrectQuestions);
    }
}
