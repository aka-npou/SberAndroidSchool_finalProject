package com.aka_npou.sberandroidschool_finalproject.data.repository;

import androidx.room.Transaction;

import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  Имплементация интерфейса {@link IStatisticRepository} репозитория для получения информации
 *  о статистики ответов из базы данных
 *
 *  @author Мулярчук Александр
 */
public class StatisticRepository implements IStatisticRepository {
    private final IStatisticDao mStatisticDao;
    private final IConverter<Statistic, StatisticEntity> mConverter;

    /**
     * Конструктор
     * @param statisticDao Dao для работы с базой данных
     * @param converter конвертер модели дата слоя в модель домейн слоя и обратно
     */
    public StatisticRepository(IStatisticDao statisticDao, IConverter<Statistic, StatisticEntity> converter) {
        this.mStatisticDao = statisticDao;
        mConverter = converter;
    }

    @Transaction
    @Override
    public void addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        mStatisticDao.addAnswerResult(new StatisticEntity(0, questionId, answerIndex, isCorrectAnswer, dateOfAnswer.getTime()));
    }

    @Override
    public List<Statistic> getStatisticForPeriod(Date from, Date to) {

        List<StatisticEntity> statisticEntityList = mStatisticDao.getStatisticForPeriod(from.getTime(), to.getTime());

        List<Statistic> statisticList = new ArrayList<>();

        for (StatisticEntity entity : statisticEntityList) {
            statisticList.add(mConverter.reverse(entity));
        }

        return statisticList;
    }
}
