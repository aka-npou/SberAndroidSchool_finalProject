package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticRepository implements IStatisticRepository {
    private final IStatisticDao mStatisticDao;
    private final IConverter<Statistic, StatisticEntity> mConverter;

    public StatisticRepository(IStatisticDao mDataBaseApi, IConverter<Statistic, StatisticEntity> converter) {
        this.mStatisticDao = mDataBaseApi;
        mConverter = converter;
    }


    @Override
    public long addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        return mStatisticDao.addAnswerResult(new StatisticEntity(0, answerIndex, isCorrectAnswer, dateOfAnswer.getTime()));
    }

    @Override
    public List<Statistic> getStatisticForPeriod(Date from, Date to) {

        List<StatisticEntity> statisticEntityList = mStatisticDao.getStatisticForPeriod(from, to);

        List<Statistic> statisticList = new ArrayList<>();

        for (StatisticEntity entity : statisticEntityList) {
            statisticList.add(mConverter.reverse(entity));
        }

        return statisticList;
    }
}
