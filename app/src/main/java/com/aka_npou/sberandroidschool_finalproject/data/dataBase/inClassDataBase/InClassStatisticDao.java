package com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.List;

public class InClassStatisticDao implements IStatisticDao {

    private final InClassDataBase inClassDataBase;

    public InClassStatisticDao(InClassDataBase inClassDataBase) {
        this.inClassDataBase = inClassDataBase;
    }

    @Override
    public boolean addAnswerResult(StatisticEntity entity) {
        return inClassDataBase.addStatistic(entity);
    }

    @Override
    public List<StatisticEntity> getStatisticForPeriod(long from, long to) {
        return inClassDataBase.getStatisticForPeriod(from, to);
    }
}
