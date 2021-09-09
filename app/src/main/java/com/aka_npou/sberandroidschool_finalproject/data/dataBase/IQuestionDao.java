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
    @Query("SELECT q.id, q.questionText, q.correctAnswerIndex FROM questions AS q LEFT JOIN answers AS a ON q.id = a.question_id LIMIT 1")
    QuestionWithAnswers getQuestion();

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
