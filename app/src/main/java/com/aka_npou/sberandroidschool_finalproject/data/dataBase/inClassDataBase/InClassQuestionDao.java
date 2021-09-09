package com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswers;

public class InClassQuestionDao implements IQuestionDao {

    private final InClassDataBase inClassDataBase;

    public InClassQuestionDao(InClassDataBase inClassDataBase) {
        this.inClassDataBase = inClassDataBase;
    }

    @Override
    public QuestionWithAnswers getQuestion() {
        return null;
    }

    @Override
    public void addQuestion(QuestionEntity entity) {

    }

    @Override
    public void addAnswers(AnswerEntity entity) {

    }
}
