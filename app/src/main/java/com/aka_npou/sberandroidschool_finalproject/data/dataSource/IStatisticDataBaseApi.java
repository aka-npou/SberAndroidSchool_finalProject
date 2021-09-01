package com.aka_npou.sberandroidschool_finalproject.data.dataSource;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;

import java.util.Date;
import java.util.List;

public interface IStatisticDataBaseApi {
    long addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer);

    List<Statistic> getStatisticForPeriod(Date from, Date to);
}
