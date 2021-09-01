package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IQuestionDataBaseApi;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

public class QuestionRepository implements IQuestionRepository {
    private final IQuestionDataBaseApi mDataBaseApi;

    public QuestionRepository(IQuestionDataBaseApi mDataBaseApi) {
        this.mDataBaseApi = mDataBaseApi;
    }

    @Override
    public Question getQuestion() {
        return mDataBaseApi.getQuestion();
    }
}
