package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.List;
import java.util.Objects;

/**
 * Модель для отображения пользователю вопроса и вариантов ответов
 *
 * @author Мулярчук Александр
 */
public class Question {
    private final long id;
    private final String questionText;
    private final List<String> answers;
    private final int correctAnswerIndex;
    private final String type;

    /**
     * Конструктор
     *
     * @param id                 идентификатор вопроса в базе данных
     * @param questionText       текст вопроса
     * @param answers            {@link List} варианты ответов
     * @param correctAnswerIndex индекс правильного ответа в answers
     */
    public Question(long id, String questionText, List<String> answers, int correctAnswerIndex, String type) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.type = type;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id
                && correctAnswerIndex == question.correctAnswerIndex
                && Objects.equals(questionText, question.questionText)
                && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, answers, correctAnswerIndex);
    }
}
