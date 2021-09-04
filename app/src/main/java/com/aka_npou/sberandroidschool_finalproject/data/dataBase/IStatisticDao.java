package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.List;

public interface IStatisticDao {
    boolean addAnswerResult(StatisticEntity entity);

    List<StatisticEntity> getStatisticForPeriod(long from, long to);
}
