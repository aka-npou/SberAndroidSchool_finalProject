package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswers;

@Dao
public interface IQuestionDao {
    @Query("SELECT q.id, q.questionText, q.correctAnswerIndex FROM questions AS q LEFT JOIN answers AS a ON q.id = a.question_id ORDER BY RANDOM() LIMIT 1")
    QuestionWithAnswers getQuestion();

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

    @Transaction
    default void insert(QuestionWithAnswers entity) {
        addQuestion(entity.questionEntity);
        for (AnswerEntity answerEntity : entity.answers) {
            addAnswers(answerEntity);
        }
    }

    @Insert
    void addQuestion(QuestionEntity entity);

    @Insert
    void addAnswers(AnswerEntity entity);


}
