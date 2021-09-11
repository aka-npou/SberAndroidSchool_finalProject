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

@Dao
public interface IQuestionDao {
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

    @Transaction
    @Query("SELECT " +
                "q.id, " +
                "q.questionText, " +
                "q.correctAnswerIndex," +
                "q.question_type " +
            "FROM questions AS q " +
                "JOIN (SELECT q.id, " +
                "COUNT(IFNULL(s.id, 0)) AS countQuestionShow " +
                "FROM questions AS q " +
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
    QuestionWithAnswersAndType getUncommonQuestion();

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

    @Insert
    void addQuestion(QuestionEntity entity);

    @Insert
    void addAnswers(AnswerEntity entity);

    @Insert
    long addType(QuestionTypeEntity entity);

    @Update(onConflict = IGNORE)
    int updateType(QuestionTypeEntity entity);

    @Query("SELECT * FROM question_types AS qt WHERE qt.type = :type")
    QuestionTypeEntity getQuestionTypeEntityOfType(String type);


}
