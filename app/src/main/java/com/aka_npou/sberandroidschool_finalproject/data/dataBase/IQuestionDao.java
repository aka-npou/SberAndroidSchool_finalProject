package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswers;

/**
 * Интерфейс для работы с базой данных с таблицей вопросов и вариантов ответов
 *
 * @author Мулярчук Александр
 */
@Dao
public interface IQuestionDao {
    /**
     * Получение рандомного вопроса с вариантами ответов
     * @return {@link QuestionWithAnswers}
     */
    @Query("SELECT q.id, q.questionText, q.correctAnswerIndex " +
            "FROM questions AS q LEFT JOIN answers AS a ON q.id = a.question_id ORDER BY RANDOM() LIMIT 1")
    QuestionWithAnswers getQuestion();

    /**
     * Получение самого редкого по показыванию вопроса с вариантами ответов
     * @return {@link QuestionWithAnswers}
     */
    @Query("SELECT " +
                "q.id, " +
                "q.questionText, " +
                "q.correctAnswerIndex " +
            "FROM questions AS q " +
                "JOIN (SELECT q.id, " +
                "SUM(CASE WHEN s.id IS NULL THEN 0 ELSE 1 END) AS countQuestionShow " +
                "FROM questions AS q " +
                "LEFT JOIN statistics AS s " +
                "ON q.id = s.questionId " +
                "GROUP BY " +
                "q.id " +
                "ORDER BY " +
                "countQuestionShow " +
                "LIMIT 1) AS uncommonQuestion " +
            "ON q.id = uncommonQuestion.id " +
            "LEFT JOIN answers AS a ON q.id = a.question_id")
    QuestionWithAnswers getUncommonQuestion();

    /**
     * Добавление вопроса в базу данных. Добавление вариантов ответа в базу данных
     * @param entity {@link QuestionWithAnswers} модель вопроса с вариантами ответов для базы данных
     */
    @Transaction
    default void insert(QuestionWithAnswers entity) {
        addQuestion(entity.questionEntity);
        for (AnswerEntity answerEntity : entity.answers) {
            addAnswers(answerEntity);
        }
    }

    /**
     * Добавление вопроса в базу данных
     * @param entity {@link QuestionEntity} модель вопроса для базы данных
     */
    @Insert
    void addQuestion(QuestionEntity entity);

    /**
     * Добавление варианта ответа в базу данных
     * @param entity {@link AnswerEntity} модель варианта ответа для базы данных
     */
    @Insert
    void addAnswers(AnswerEntity entity);


}
