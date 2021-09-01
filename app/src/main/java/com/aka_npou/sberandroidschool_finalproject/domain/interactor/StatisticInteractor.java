package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;

public class StatisticInteractor implements IStatisticInteractor {
    private final IStatisticRepository mStatisticRepository;

    public StatisticInteractor(IStatisticRepository statisticRepository) {
        mStatisticRepository = statisticRepository;
    }

    @Override
    public Single<Long> addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        return Single.fromCallable(() -> mStatisticRepository.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer));
    }

    @Override
    public Single<List<Statistic>> getStatisticForPeriod(Date from, Date to) {
        return Single.fromCallable(() -> mStatisticRepository.getStatisticForPeriod(from, to));
    }
}
