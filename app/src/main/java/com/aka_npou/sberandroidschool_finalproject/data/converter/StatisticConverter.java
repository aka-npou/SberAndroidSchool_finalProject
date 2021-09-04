package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;

import java.util.Date;

public class StatisticConverter implements IConverter<Statistic, StatisticEntity> {
    @NonNull
    @Override
    public StatisticEntity convert(@NonNull Statistic item) {
        StatisticEntity entity = new StatisticEntity();
        entity.id = item.getId();
        entity.questionId = item.getQuestionId();
        entity.dateOfAnswer = item.getDateOfAnswer().getTime();
        entity.answerIndex = item.getAnswerIndex();
        entity.isCorrectAnswer = item.isCorrectAnswer();

        return entity;
    }

    @NonNull
    @Override
    public Statistic reverse(@NonNull StatisticEntity item) {
        Statistic statistic = new Statistic(item.id,
                item.questionId,
                item.answerIndex,
                item.isCorrectAnswer,
                new Date(item.dateOfAnswer));

        return statistic;
    }
}
