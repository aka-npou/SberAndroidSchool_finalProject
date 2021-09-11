package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.List;

/**
 * Интерфейс для работы с базой данных с таблицей статистики ответов
 *
 * @author Мулярчук Александр
 */
@Dao
public interface IStatisticDao {
    /**
     * Добавление в статистику результата ответа на вопрос
     * @param entity {@link StatisticEntity} модель результата ответа на вопрос для базы данных
     */
    @Insert
    void addAnswerResult(StatisticEntity entity);

    /**
     * Получение списка результатов ответов на вопросы за заданный период
     * @param from период с в миллисекундах
     * @param to период по в миллисекундах
     * @return список {@link List} из {@link StatisticEntity} результатов ответов на вопросы
     */
    @Query("SELECT * FROM statistics WHERE dateOfAnswer >= :from and dateOfAnswer <= :to")
    List<StatisticEntity> getStatisticForPeriod(long from, long to);
}
