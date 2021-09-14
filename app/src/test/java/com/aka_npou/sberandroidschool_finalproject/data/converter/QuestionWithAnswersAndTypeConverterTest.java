package com.aka_npou.sberandroidschool_finalproject.data.converter;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionTypeEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswersAndType;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class QuestionWithAnswersAndTypeConverterTest {

    private QuestionWithAnswersAndTypeConverter questionWithAnswersAndTypeConverter;

    @Before
    public void setup() {
        questionWithAnswersAndTypeConverter = new QuestionWithAnswersAndTypeConverter();
    }

    @Test
    public void convertQuestionTest() {
        //Arrange
        QuestionWithAnswersAndType expectedResult = new QuestionWithAnswersAndType();
        expectedResult.answers = Arrays.asList(new AnswerEntity(0, "test answer1", 1), new AnswerEntity(0, "test answer2", 1));
        expectedResult.questionEntity = new QuestionEntity(1, "test", 1);

        Question question = new Question(1, "test", Arrays.asList("test answer1", "test answer2"), 1, "test_type");
        //Act
        QuestionWithAnswersAndType actualResult = questionWithAnswersAndTypeConverter.convert(question);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void convertQuestionThrowExceptionTest() {
        //Arrange
        QuestionWithAnswersAndType expectedResult = new QuestionWithAnswersAndType();
        expectedResult.answers = Arrays.asList(new AnswerEntity(0, "test answer1", 1), new AnswerEntity(0, "test answer2", 1));
        expectedResult.questionEntity = new QuestionEntity(1, "test", 1);

        Question question = null;
        //Act
        QuestionWithAnswersAndType actualResult = questionWithAnswersAndTypeConverter.convert(question);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void reverseQuestionTest() {
        //Arrange
        Question expectedResult = new Question(1, "test", Arrays.asList("test answer1", "test answer2"), 1, "test_type");

        QuestionWithAnswersAndType questionWithAnswersAndType = new QuestionWithAnswersAndType();
        questionWithAnswersAndType.answers = Arrays.asList(new AnswerEntity(0, "test answer1", 1), new AnswerEntity(0, "test answer2", 1));
        questionWithAnswersAndType.questionEntity = new QuestionEntity(1, "test", 1);
        questionWithAnswersAndType.type = new QuestionTypeEntity(1, "test_type");
        //Act
        Question actualResult = questionWithAnswersAndTypeConverter.reverse(questionWithAnswersAndType);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void reverseQuestionThrowExceptionTest() {
        //Arrange
        Question expectedResult = new Question(1, "test", Arrays.asList("test answer1", "test answer2"), 1, "test_type");
        QuestionWithAnswersAndType questionWithAnswersAndType = null;
        //Act
        Question actualResult = questionWithAnswersAndTypeConverter.reverse(questionWithAnswersAndType);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

}