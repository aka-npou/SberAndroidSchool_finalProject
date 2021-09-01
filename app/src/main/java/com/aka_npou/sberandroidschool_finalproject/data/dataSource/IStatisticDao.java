package com.aka_npou.sberandroidschool_finalproject.data.dataSource;

import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.Date;
import java.util.List;

public interface IStatisticDao {
    long addAnswerResult(StatisticEntity entity);

    List<StatisticEntity> getStatisticForPeriod(Date from, Date to);
}
