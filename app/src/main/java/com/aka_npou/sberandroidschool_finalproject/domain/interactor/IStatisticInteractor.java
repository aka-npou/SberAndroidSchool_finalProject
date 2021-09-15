package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;
import com.aka_npou.sberandroidschool_finalproject.domain.model.TotalStatistic;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Интерактор для получения информации о статистике по ответам
 *
 * @author Мулярчук Александр
 */
public interface IStatisticInteractor {
    /**
     * Создает задачу по добавлению статистики по ответу
     *
     * @param questionId      идентификатор вопроса
     * @param answerIndex     индекс выбранного ответа
     * @param isCorrectAnswer признак правильности ответа
     * @param dateOfAnswer    дата ответа
     * @return {@link Completable} RxJava объект выполения задачи по добавлению статистики по ответу
     */
    Completable addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer);

    /**
     * Создает задачу по получению статистики по ответам за период
     *
     * @param from дата с которой получать статистику
     * @param to   дата по которую получать статистику
     * @return {@link Single} RxJava объект выполения задачи по получению статистики по ответам за период
     */
    Single<List<DailyStatistics>> getStatisticForPeriod(Date from, Date to);

    /**
     * Создает задачу по получению общей статистики
     *
     * @return {@link Single} RxJava объект выполения задачи по получению общей статистики
     */
    Single<TotalStatistic> getTotalStatistic();

    /**
     * Создает задачу получение детальной статистики за день
     * @param from дата с которой получается детальной статистики
     * @param to дата до которой получается дательная статистика
     * @return {@link Single} RxJava объект выполения задачи по получению детальной статистики за день
     */
    Single<List<DetailedStatisticPerPeriod>> getExplicitStatisticForPeriod(Date from, Date to);
}
