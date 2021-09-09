package com.aka_npou.sberandroidschool_finalproject.data.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class QuestionWithAnswers {
    @Embedded
    public QuestionEntity questionEntity;
    @Relation(parentColumn = "id", entityColumn = "question_id")
    public List<AnswerEntity> answers;
}
