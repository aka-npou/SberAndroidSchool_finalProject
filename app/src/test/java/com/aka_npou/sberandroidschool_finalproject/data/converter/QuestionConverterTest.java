package com.aka_npou.sberandroidschool_finalproject.data.converter;

import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class QuestionConverterTest {

    private QuestionConverter questionConverter;

    @Before
    public void setup() {
        questionConverter = new QuestionConverter();
    }

    @Test
    public void convertQuestionTest() {
        //Arrange
        QuestionEntity expectedResult = new QuestionEntity(1, "test", 1);
        Question question = new Question(1, "test", new ArrayList<>(), 1);
        //Act
        QuestionEntity actualResult = questionConverter.convert(question);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void convertQuestionThrowExceptionTest() {
        //Arrange
        QuestionEntity expectedResult = new QuestionEntity(1, "test", 1);
        Question question = null;
        //Act
        QuestionEntity actualResult = questionConverter.convert(question);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void reverseQuestionTest() {
        //Arrange
        Question expectedResult = new Question(1, "test", new ArrayList<>(), 1);
        QuestionEntity questionEntity = new QuestionEntity(1, "test", 1);
        //Act
        Question actualResult = questionConverter.reverse(questionEntity);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void reverseQuestionThrowExceptionTest() {
        //Arrange
        Question expectedResult = new Question(1, "test", new ArrayList<>(), 1);;
        QuestionEntity questionEntity = null;
        //Act
        Question actualResult = questionConverter.reverse(questionEntity);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

}