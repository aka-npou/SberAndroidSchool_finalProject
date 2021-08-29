package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IDataBaseApi;
import com.aka_npou.sberandroidschool_finalproject.data.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

public class InClassQuestionRepository implements IQuestionRepository {
    private final IDataBaseApi mDataBaseApi;

    public InClassQuestionRepository(IDataBaseApi mDataBaseApi) {
        this.mDataBaseApi = mDataBaseApi;
    }

    @Override
    public Question getQuestion() {
        return mDataBaseApi.getQuestion();
    }
}
