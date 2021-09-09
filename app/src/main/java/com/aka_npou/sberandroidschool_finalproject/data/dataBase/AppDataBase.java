package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

@Database(entities = {QuestionEntity.class, StatisticEntity.class, AnswerEntity.class},
        version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract IQuestionDao getQuestionDao();
    public abstract IStatisticDao getStatisticDao();
}
