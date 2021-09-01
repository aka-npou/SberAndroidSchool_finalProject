package com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.Date;
import java.util.List;

public class InClassStatisticDao implements IStatisticDao {

    private final InClassDataBase inClassDataBase;

    public InClassStatisticDao(InClassDataBase inClassDataBase) {
        this.inClassDataBase = inClassDataBase;
    }

    @Override
    public long addAnswerResult(StatisticEntity entity) {
        return inClassDataBase.addStatistic(entity);
    }

    @Override
    public List<StatisticEntity> getStatisticForPeriod(Date from, Date to) {
        return inClassDataBase.getStatisticForPeriod(from, to);
    }
}
