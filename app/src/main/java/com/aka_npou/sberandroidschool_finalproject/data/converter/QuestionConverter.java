package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionConverter implements IConverter<Question, QuestionEntity> {
    @NonNull
    @Override
    public QuestionEntity convert(@NonNull Question item) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.id = item.getId();
        questionEntity.questionText = item.getQuestionText();
        questionEntity.correctAnswerIndex = item.getCorrectAnswerIndex();

        for (String answer : item.getAnswers()) {
            questionEntity.answers.add(answer);
        }

        return questionEntity;
    }

    @NonNull
    @Override
    public Question reverse(@NonNull QuestionEntity item) {
        List<String> answers = new ArrayList<>();

        for (String answer : item.answers) {
            answers.add(answer);
        }

        Question question = new Question(item.id, item.questionText, answers, item.correctAnswerIndex);
        return question;
    }
}