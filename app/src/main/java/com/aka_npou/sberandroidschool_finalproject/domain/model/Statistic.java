package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.Date;
import java.util.Objects;

/**
 * Модель для отображения пользователю статистики ответов
 *
 * @author Мулярчук Александр
 */
public class Statistic {
    private final long id;
    private final long questionId;
    private final int answerIndex;
    private final boolean isCorrectAnswer;
    private final Date dateOfAnswer;

    /**
     * Конструктор
     * @param id идентификатор в базе данных
     * @param questionId идентификатор вопроса
     * @param answerIndex индекс выбранного ответа
     * @param isCorrectAnswer признак правильности ответа
     * @param dateOfAnswer дата ответа
     */
    public Statistic(long id, long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        this.id = id;
        this.questionId = questionId;
        this.answerIndex = answerIndex;
        this.isCorrectAnswer = isCorrectAnswer;
        this.dateOfAnswer = dateOfAnswer;
    }

    public long getId() {
        return id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public Date getDateOfAnswer() {
        return dateOfAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return id == statistic.id && questionId == statistic.questionId && answerIndex == statistic.answerIndex && isCorrectAnswer == statistic.isCorrectAnswer && Objects.equals(dateOfAnswer, statistic.dateOfAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, answerIndex, isCorrectAnswer, dateOfAnswer);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answerIndex=" + answerIndex +
                ", isCorrectAnswer=" + isCorrectAnswer +
                ", dateOfAnswer=" + dateOfAnswer +
                '}';
    }
}
