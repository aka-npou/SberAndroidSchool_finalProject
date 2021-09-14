package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswers;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionWithAnswersConverter implements IConverter<Question, QuestionWithAnswers> {
    @NonNull
    @Override
    public QuestionWithAnswers convert(@NonNull Question item) {
        QuestionWithAnswers entity = new QuestionWithAnswers();
        entity.questionEntity = new QuestionEntity(item.getId(), item.getQuestionText(), item.getCorrectAnswerIndex());
        entity.answers = new ArrayList<>();

        for (String answer : item.getAnswers()) {
            AnswerEntity answerEntity = new AnswerEntity();
            answerEntity.questionId = item.getId();
            answerEntity.text = answer;

            entity.answers.add(answerEntity);
        }

        return entity;
    }

    @NonNull
    @Override
    public Question reverse(@NonNull QuestionWithAnswers item) {
        List<String> answers = new ArrayList<>();

        for (AnswerEntity answerEntity : item.answers) {
            answers.add(answerEntity.text);
        }

        return new Question(item.questionEntity.id,
                item.questionEntity.questionText,
                answers,
                item.questionEntity.correctAnswerIndex);
    }
}

