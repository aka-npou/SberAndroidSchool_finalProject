package com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IDataBaseApi;
import com.aka_npou.sberandroidschool_finalproject.data.model.Question;

public class InClassDataBaseApi implements IDataBaseApi {

    private final InClassDataBase inClassDataBase;

    public InClassDataBaseApi(InClassDataBase inClassDataBase) {
        this.inClassDataBase = inClassDataBase;
    }

    @Override
    public Question getQuestion() {
        return inClassDataBase.getQuestion();
    }
}
