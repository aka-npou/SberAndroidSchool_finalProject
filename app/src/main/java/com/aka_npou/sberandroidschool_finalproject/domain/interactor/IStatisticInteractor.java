package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;

public interface IStatisticInteractor {
    Single<Long> addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer);
    Single<List<Statistic>> getStatisticForPeriod(Date from, Date to);
}
