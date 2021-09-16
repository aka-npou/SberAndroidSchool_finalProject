package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionTypeEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswersAndType;

import java.util.List;

/**
 * Интерфейс для работы с базой данных с таблицей вопросов и вариантов ответов
 *
 * @author Мулярчук Александр
 */
@Dao
public interface IQuestionDao {
    /**
     * Получение рандомного вопроса с вариантами ответов
     *
     * @return {@link QuestionWithAnswersAndType}
     */
    @Transaction
    @Query("SELECT q.id, " +
            "q.questionText, " +
            "q.correctAnswerIndex," +
            "q.question_type " +
            "FROM questions AS q " +
            "LEFT JOIN answers AS a " +
            "ON q.id = a.question_id " +
            "LEFT JOIN question_types AS qt " +
            "ON q.question_type = qt.id " +
            "ORDER BY RANDOM() " +
            "LIMIT 1")
    QuestionWithAnswersAndType getQuestion();

    /**
     * Получение самого редкого по показыванию вопроса с вариантами ответов
     *
     * @return {@link QuestionWithAnswersAndType}
     */
    @Query("SELECT " +
            "q.id, " +
            "q.questionText, " +
            "q.correctAnswerIndex," +
            "q.question_type " +
            "FROM questions AS q " +
            "JOIN (SELECT q.id, " +
            "SUM(CASE WHEN s.id IS NULL THEN 0 ELSE 1 END) AS countQuestionShow " +
            "FROM questions AS q " +
            "INNER JOIN question_types AS qt " +
            "ON q.question_type = qt.id " +
            "AND qt.type = :typeQuestions " +
            "LEFT JOIN statistics AS s " +
            "ON q.id = s.questionId " +
            "GROUP BY " +
            "q.id " +
            "ORDER BY " +
            "countQuestionShow " +
            "LIMIT 1) AS uncommonQuestion " +
            "ON q.id = uncommonQuestion.id " +
            "LEFT JOIN answers AS a ON q.id = a.question_id " +
            "LEFT JOIN question_types AS qt " +
            "ON q.question_type = qt.id")
    QuestionWithAnswersAndType getUncommonQuestion(String typeQuestions);

    /**
     * Добавление вопроса в базу данных. Добавление вариантов ответа в базу данных
     *
     * @param entity {@link QuestionWithAnswersAndType} модель вопроса с вариантами ответов для базы данных
     */
    @Transaction
    default void insert(QuestionWithAnswersAndType entity) {
        QuestionTypeEntity questionTypeEntity = getQuestionTypeEntityOfType(entity.type.type);
        if (questionTypeEntity == null) {
            entity.questionEntity.questionType = addType(entity.type);
        } else {
            entity.questionEntity.questionType = questionTypeEntity.id;
        }

        addQuestion(entity.questionEntity);
        for (AnswerEntity answerEntity : entity.answers) {
            addAnswers(answerEntity);
        }
    }

    /**
     * Добавление вопроса в базу данных
     *
     * @param entity {@link QuestionEntity} модель вопроса для базы данных
     */
    @Insert
    void addQuestion(QuestionEntity entity);

    /**
     * Добавление варианта ответа в базу данных
     *
     * @param entity {@link AnswerEntity} модель варианта ответа для базы данных
     */
    @Insert
    void addAnswers(AnswerEntity entity);

    /**
     * Добавление типа вопроса в базу данных
     *
     * @param entity {@link QuestionTypeEntity} модель типа вопроса для базы данных
     */
    @Insert
    long addType(QuestionTypeEntity entity);

    /**
     * Обновление типа вопроса в базе данных
     *
     * @param entity {@link QuestionTypeEntity} модель типа вопроса для базы данных
     * @return количество измененных записей
     */
    @Update(onConflict = IGNORE)
    int updateType(QuestionTypeEntity entity);

    /**
     * Получение типов вопроса по имени типа
     *
     * @param type имя типа вопроса
     * @return {@link QuestionTypeEntity} модель типа вопроса для базы данных
     */
    @Query("SELECT * FROM question_types AS qt WHERE qt.type = :type")
    QuestionTypeEntity getQuestionTypeEntityOfType(String type);


    /**
     * Получение типов вопроса
     *
     * @return Список {@link List} из {@link String} типов вопроса
     */
    @Query("SELECT * FROM question_types")
    List<QuestionTypeEntity> getQuestionTypes();
}
