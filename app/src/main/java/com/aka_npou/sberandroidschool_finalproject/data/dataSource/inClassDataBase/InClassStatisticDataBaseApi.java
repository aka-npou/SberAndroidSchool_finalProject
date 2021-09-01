package com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IStatisticDataBaseApi;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;

import java.util.Date;
import java.util.List;

public class InClassStatisticDataBaseApi implements IStatisticDataBaseApi {

    private final InClassDataBase inClassDataBase;

    public InClassStatisticDataBaseApi(InClassDataBase inClassDataBase) {
        this.inClassDataBase = inClassDataBase;
    }

    @Override
    public long addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        return inClassDataBase.addStatistic(new Statistic(questionId, answerIndex, isCorrectAnswer, dateOfAnswer));
    }

    @Override
    public List<Statistic> getStatisticForPeriod(Date from, Date to) {
        return inClassDataBase.getStatisticForPeriod(from, to);
    }
}
