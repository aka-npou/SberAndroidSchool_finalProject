package com.aka_npou.sberandroidschool_finalproject.data.repository;

import androidx.room.Transaction;

import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.DetailedStatisticPerPeriodEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.TotalStatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.model.TotalStatistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Имплементация интерфейса {@link IStatisticRepository} репозитория для получения информации
 * о статистики ответов из базы данных
 *
 * @author Мулярчук Александр
 */
public class StatisticRepository implements IStatisticRepository {
    private final IStatisticDao statisticDao;
    private final IConverter<Statistic, StatisticEntity> converter;
    private final IConverter<TotalStatistic, TotalStatisticEntity> totalStatisticConverter;
    private final IConverter<DetailedStatisticPerPeriod, DetailedStatisticPerPeriodEntity> detailedStatisticPerPeriodConverter;

    /**
     * Конструктор
     *
     * @param statisticDao Dao для работы с базой данных
     * @param converter    конвертер модели дата слоя в модель домейн слоя и обратно
     */
    public StatisticRepository(IStatisticDao statisticDao,
                               IConverter<Statistic, StatisticEntity> converter,
                               IConverter<TotalStatistic, TotalStatisticEntity> totalStatisticConverter,
                               IConverter<DetailedStatisticPerPeriod, DetailedStatisticPerPeriodEntity> detailedStatisticPerPeriodConverter) {
        this.statisticDao = statisticDao;
        this.converter = converter;
        this.totalStatisticConverter = totalStatisticConverter;
        this.detailedStatisticPerPeriodConverter = detailedStatisticPerPeriodConverter;
    }

    @Transaction
    @Override
    public void addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        StatisticEntity statisticEntity = new StatisticEntity(0,
                questionId,
                answerIndex,
                isCorrectAnswer,
                dateOfAnswer.getTime());

        statisticDao.addAnswerResult(statisticEntity);
    }

    @Override
    public List<Statistic> getStatisticForPeriod(Date from, Date to) {

        List<StatisticEntity> statisticEntityList = statisticDao.getStatisticForPeriod(from.getTime(), to.getTime());

        List<Statistic> statisticList = new ArrayList<>();

        for (StatisticEntity entity : statisticEntityList) {
            statisticList.add(converter.reverse(entity));
        }

        return statisticList;
    }

    @Override
    public TotalStatistic getTotalStatistic() {
        return totalStatisticConverter.reverse(statisticDao.getTotalStatistic());
    }

    @Override
    public List<DetailedStatisticPerPeriod> getExplicitStatisticForPeriod(Date from, Date to) {
        List<DetailedStatisticPerPeriodEntity> listEntities = statisticDao.getExplicitStatisticForPeriod(from.getTime(), to.getTime());

        List<DetailedStatisticPerPeriod> list = new ArrayList<>();

        for (DetailedStatisticPerPeriodEntity entity : listEntities) {
            list.add(detailedStatisticPerPeriodConverter.reverse(entity));
        }

        return list;
    }
}
