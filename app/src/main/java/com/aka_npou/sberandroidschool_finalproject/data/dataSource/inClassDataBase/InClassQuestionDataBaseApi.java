package com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IQuestionDataBaseApi;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;

public class InClassQuestionDataBaseApi implements IQuestionDataBaseApi {

    private final InClassDataBase inClassDataBase;

    public InClassQuestionDataBaseApi(InClassDataBase inClassDataBase) {
        this.inClassDataBase = inClassDataBase;
    }

    @Override
    public Question getQuestion() {
        return inClassDataBase.getQuestion();
    }
}
