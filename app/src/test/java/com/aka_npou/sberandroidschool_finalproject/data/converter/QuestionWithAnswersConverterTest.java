package com.aka_npou.sberandroidschool_finalproject.data.converter;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswers;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class QuestionWithAnswersConverterTest {

    private QuestionWithAnswersConverter questionWithAnswersConverter;

    @Before
    public void setup() {
        questionWithAnswersConverter = new QuestionWithAnswersConverter();
    }

    @Test
    public void convertQuestionTest() {
        //Arrange
        QuestionWithAnswers expectedResult = new QuestionWithAnswers();
        expectedResult.answers = Arrays.asList(new AnswerEntity(0, "test answer1", 1), new AnswerEntity(0, "test answer2", 1));
        expectedResult.questionEntity = new QuestionEntity(1, "test", 1);

        Question question = new Question(1, "test", Arrays.asList("test answer1", "test answer2"), 1);
        //Act
        QuestionWithAnswers actualResult = questionWithAnswersConverter.convert(question);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void convertQuestionThrowExceptionTest() {
        //Arrange
        QuestionWithAnswers expectedResult = new QuestionWithAnswers();
        expectedResult.answers = Arrays.asList(new AnswerEntity(0, "test answer1", 1), new AnswerEntity(0, "test answer2", 1));
        expectedResult.questionEntity = new QuestionEntity(1, "test", 1);

        Question question = null;
        //Act
        QuestionWithAnswers actualResult = questionWithAnswersConverter.convert(question);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void reverseQuestionTest() {
        //Arrange
        Question expectedResult = new Question(1, "test", Arrays.asList("test answer1", "test answer2"), 1);

        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers();
        questionWithAnswers.answers = Arrays.asList(new AnswerEntity(0, "test answer1", 1), new AnswerEntity(0, "test answer2", 1));
        questionWithAnswers.questionEntity = new QuestionEntity(1, "test", 1);
        //Act
        Question actualResult = questionWithAnswersConverter.reverse(questionWithAnswers);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void reverseQuestionThrowExceptionTest() {
        //Arrange
        Question expectedResult = new Question(1, "test", Arrays.asList("test answer1", "test answer2"), 1);;
        QuestionWithAnswers questionWithAnswers = null;
        //Act
        Question actualResult = questionWithAnswersConverter.reverse(questionWithAnswers);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

}