package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

public class QuestionRepository implements IQuestionRepository {
    private final IQuestionDao mQuestionDao;
    private final IConverter<Question, QuestionEntity> mConverter;


    public QuestionRepository(IQuestionDao mDataBaseApi, IConverter<Question, QuestionEntity> converter) {
        this.mQuestionDao = mDataBaseApi;
        mConverter = converter;
    }

    @Override
    public Question getQuestion() {
        return mConverter.reverse(mQuestionDao.getQuestion());
    }
}
