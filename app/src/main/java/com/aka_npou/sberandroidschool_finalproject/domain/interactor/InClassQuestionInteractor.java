package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.data.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

import io.reactivex.Single;

public class InClassQuestionInteractor implements IQuestionInteractor{
    private final IQuestionRepository mQuestionRepository;

    public InClassQuestionInteractor(IQuestionRepository mQuestionRepository) {
        this.mQuestionRepository = mQuestionRepository;
    }

    @Override
    public Single<Question> getQuestion() {
        return Single.fromCallable(() -> mQuestionRepository.getQuestion());
    }
}
