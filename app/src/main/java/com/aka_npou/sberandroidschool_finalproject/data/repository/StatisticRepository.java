package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IStatisticDataBaseApi;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;

import java.util.Date;
import java.util.List;

public class StatisticRepository implements IStatisticRepository {
    private final IStatisticDataBaseApi mDataBaseApi;

    public StatisticRepository(IStatisticDataBaseApi mDataBaseApi) {
        this.mDataBaseApi = mDataBaseApi;
    }


    @Override
    public long addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        return mDataBaseApi.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer);
    }

    @Override
    public List<Statistic> getStatisticForPeriod(Date from, Date to) {
        return mDataBaseApi.getStatisticForPeriod(from, to);
    }
}
