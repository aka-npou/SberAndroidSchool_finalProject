package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

import io.reactivex.Single;

public class QuestionInteractor implements IQuestionInteractor{
    private final IQuestionRepository mQuestionRepository;

    public QuestionInteractor(IQuestionRepository questionRepository) {
        this.mQuestionRepository = questionRepository;
    }

    @Override
    public Single<Question> getQuestion() {
        return Single.fromCallable(mQuestionRepository::getQuestion);
    }
}
