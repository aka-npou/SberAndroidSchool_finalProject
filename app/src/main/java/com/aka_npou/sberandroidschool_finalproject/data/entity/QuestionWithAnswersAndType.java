package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
import java.util.Objects;

/**
 * Модель для получения вопроса с вариантами ответов
 *
 * @author Мулярчук Александр
 */
public class QuestionWithAnswersAndType {
    @Embedded
    public QuestionEntity questionEntity;
    @Relation(parentColumn = "id", entityColumn = "question_id")
    public List<AnswerEntity> answers;
    @Relation(parentColumn = "question_type", entityColumn = "id")
    public QuestionTypeEntity type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionWithAnswers that = (QuestionWithAnswers) o;
        return Objects.equals(questionEntity, that.questionEntity) && Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionEntity, answers);
    }
}
