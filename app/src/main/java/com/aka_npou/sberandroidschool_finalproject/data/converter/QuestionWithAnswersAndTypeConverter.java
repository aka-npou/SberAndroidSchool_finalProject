package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionTypeEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswersAndType;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Имплементация интерфейса {@link IConverter} для конвертации {@link Question} в {@link QuestionWithAnswersAndType}
 *
 * @author Мулярчук Александр
 */
public class QuestionWithAnswersAndTypeConverter implements IConverter<Question, QuestionWithAnswersAndType> {
    @NonNull
    @Override
    public QuestionWithAnswersAndType convert(@NonNull Question item) {
        QuestionWithAnswersAndType entity = new QuestionWithAnswersAndType();
        entity.questionEntity = new QuestionEntity(item.getId(), item.getQuestionText(), item.getCorrectAnswerIndex());
        entity.answers = new ArrayList<>();

        for (String answer : item.getAnswers()) {
            AnswerEntity answerEntity = new AnswerEntity();
            answerEntity.questionId = item.getId();
            answerEntity.text = answer;

            entity.answers.add(answerEntity);
        }

        QuestionTypeEntity questionTypeEntity = new QuestionTypeEntity();
        questionTypeEntity.type = item.getType();
        entity.type = questionTypeEntity;

        return entity;
    }

    @NonNull
    @Override
    public Question reverse(@NonNull QuestionWithAnswersAndType item) {
        List<String> answers = new ArrayList<>();

        for (AnswerEntity answerEntity : item.answers) {
            answers.add(answerEntity.text);
        }

        return new Question(item.questionEntity.id, item.questionEntity.questionText, answers, item.questionEntity.correctAnswerIndex, item.type.type);
    }
}

