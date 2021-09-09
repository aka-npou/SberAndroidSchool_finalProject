package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.List;

@Dao
public interface IStatisticDao {
    @Insert
    void addAnswerResult(StatisticEntity entity);

    @Query("SELECT * FROM statistics WHERE dateOfAnswer >= :from and dateOfAnswer <= :to")
    List<StatisticEntity> getStatisticForPeriod(long from, long to);
}
