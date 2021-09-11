package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

/**
 * Реализация RoomDatabase для приложения
 * @author Мулярчук Александр
 */

@Database(entities = {QuestionEntity.class, StatisticEntity.class, AnswerEntity.class},
        version = 1)
public abstract class AppDataBase extends RoomDatabase {
    /**
     * Получение Dao для работы с базой данных
     * @return {@link IQuestionDao} или его имплементацию
     */
    public abstract IQuestionDao getQuestionDao();

    /**
     * Получение Dao для работы с базой данных
     * @return {@link IStatisticDao} или его имплементацию
     */
    public abstract IStatisticDao getStatisticDao();
}
