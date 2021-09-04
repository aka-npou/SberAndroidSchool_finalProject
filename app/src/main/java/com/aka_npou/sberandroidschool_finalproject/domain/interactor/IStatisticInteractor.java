package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IStatisticInteractor {
    Completable addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer);
    Single<List<DailyStatistics>> getStatisticForPeriod(Date from, Date to);
}
