package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;

import io.reactivex.Single;

public interface IQuestionInteractor {
        Single<Question> getQuestion();
}
