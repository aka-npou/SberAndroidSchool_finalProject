package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;

import java.util.Calendar;
import java.util.Date;

/**
 * Имплементация интерфейса {@link IConverter} для конвертации {@link Statistic} в {@link StatisticEntity}
 *
 * @author Мулярчук Александр
 */
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

        return new Statistic(item.id,
                item.questionId,
                item.answerIndex,
                item.isCorrectAnswer,
                getStartDate(item.dateOfAnswer));
    }

    private Date getStartDate(long dateOfAnswer) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateOfAnswer);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                0,
                0,
                0);

        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
