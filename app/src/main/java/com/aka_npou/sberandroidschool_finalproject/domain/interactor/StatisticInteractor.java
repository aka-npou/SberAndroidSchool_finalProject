package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.model.TotalStatistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Имплементация интерфейса {@link IStatisticInteractor}
 *
 * @author Мулярчук Александр
 */
public class StatisticInteractor implements IStatisticInteractor {
    private final IStatisticRepository statisticRepository;

    /**
     * Конструктор
     *
     * @param statisticRepository {@link IStatisticRepository} репозиторий для работы со статистикой ответов
     */
    public StatisticInteractor(IStatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Completable addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        return Completable.fromCallable(() -> {
            statisticRepository.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer);
            return true;
        });
    }

    @Override
    public Single<List<DailyStatistics>> getStatisticForPeriod(Date from, Date to) {
        return Single.fromCallable(() -> getDailyStatistic(from, to));
    }

    @Override
    public Single<TotalStatistic> getTotalStatistic() {
        return Single.fromCallable(statisticRepository::getTotalStatistic);
    }

    @Override
    public Single<List<DetailedStatisticPerPeriod>> getExplicitStatisticForPeriod(Date from, Date to) {
        return Single.fromCallable(() -> statisticRepository.getExplicitStatisticForPeriod(from, to));
    }

    private List<DailyStatistics> getDailyStatistic(Date from, Date to) {
        List<Statistic> statisticList = statisticRepository.getStatisticForPeriod(from, to);

        Map<Long, DailyStatistics> dailyStatisticsMap = new HashMap<>();

        for (Statistic item : statisticList) {
            if (!dailyStatisticsMap.containsKey(item.getDateOfAnswer().getTime())) {
                dailyStatisticsMap.put(item.getDateOfAnswer().getTime(), new DailyStatistics(item.getDateOfAnswer()));
            }

            DailyStatistics dailyStatistics = dailyStatisticsMap.get(item.getDateOfAnswer().getTime());

            dailyStatistics.setCountQuestions(dailyStatistics.getCountQuestions() + 1);
            if (item.isCorrectAnswer()) {
                dailyStatistics.setCountCorrectQuestions(dailyStatistics.getCountCorrectQuestions() + 1);
            }
        }

        for (long date = from.getTime(); date <= to.getTime(); date += 60 * 60 * 24 * 1000) {
            if (!dailyStatisticsMap.containsKey(date)) {
                dailyStatisticsMap.put(date, new DailyStatistics(new Date(date)));
            }
        }


        List<DailyStatistics> dailyStatisticsList = new ArrayList<>(dailyStatisticsMap.values());
        Collections.sort(dailyStatisticsList, (o1, o2) ->
                (int) (o1.getDateOfAnswer().getTime() - o2.getDateOfAnswer().getTime()));

        return dailyStatisticsList;
    }
}