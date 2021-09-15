package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aka_npou.sberandroidschool_finalproject.data.entity.DetailedStatisticPerPeriodEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.TotalStatisticEntity;

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

    @Query("SELECT " +
            "COUNT(s.id) AS countQuestions, " +
            "SUM(IFNULL(CASE WHEN s.isCorrectAnswer THEN 1 ELSE 0 END, 0)) AS countCorrectAnswers," +
            "MIN(s.dateOfAnswer) AS firstDay " +
            "FROM statistics AS s " +
            "JOIN questions AS q " +
            "ON s.questionId = q.id " +
            "   JOIN question_types AS qt " +
            "   ON q.question_type = qt.id")
    TotalStatisticEntity getTotalStatistic();

    @Query("SELECT " +
            "qt.type, " +
            "COUNT(s.id) AS countQuestions, " +
            "SUM(IFNULL(CASE WHEN s.isCorrectAnswer THEN 1 ELSE 0 END, 0)) AS countCorrectQuestions " +
            "FROM statistics AS s " +
            "JOIN questions AS q " +
            "ON s.questionId = q.id " +
            "AND s.dateOfAnswer >= :from AND s.dateOfAnswer <= :to " +
            "   JOIN question_types AS qt " +
            "   ON q.question_type = qt.id " +
            "GROUP BY " +
            "   qt.type")
    List<DetailedStatisticPerPeriodEntity> getExplicitStatisticForPeriod(long from, long to);
}
