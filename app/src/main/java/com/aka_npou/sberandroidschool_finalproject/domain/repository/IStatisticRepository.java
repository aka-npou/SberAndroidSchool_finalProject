package com.aka_npou.sberandroidschool_finalproject.domain.repository;

import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.model.TotalStatistic;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс репозитория для получения данных по статистике ответов
 *
 * @author Мулярчук Александр
 */
public interface IStatisticRepository {
    /**
     * Добавляет результат ответа на вопрос
     * @param questionId идентификатор вопроса
     * @param answerIndex индекс выбранного ответа
     * @param isCorrectAnswer признак правильности ответа
     * @param dateOfAnswer дата ответа
     */
    void addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer);

    /**
     * Получает статистику ответов за период
     * @param from дата с которой получать статистику
     * @param to дата по которую получать статистику
     * @return список {@link List} из {@link Statistic}
     */
    List<Statistic> getStatisticForPeriod(Date from, Date to);

    TotalStatistic getTotalStatistic();

    List<DetailedStatisticPerPeriod> getExplicitStatisticForPeriod(Date from, Date to);
}