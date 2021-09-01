package com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;

public class InClassQuestionDao implements IQuestionDao {

    private final InClassDataBase inClassDataBase;

    public InClassQuestionDao(InClassDataBase inClassDataBase) {
        this.inClassDataBase = inClassDataBase;
    }

    @Override
    public QuestionEntity getQuestion() {
        return inClassDataBase.getQuestion();
    }
}
