package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import static org.mockito.Mockito.when;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import io.reactivex.Single;

@RunWith(MockitoJUnitRunner.class)
public class QuestionInteractorTest {

    @Mock
    IQuestionRepository questionRepository;

    IQuestionInteractor questionInteractor;

    @Before
    public void setup() {
        questionInteractor = new QuestionInteractor(questionRepository);
    }

    @Test
    public void getQuestionTest() {
        //Arrange
        Question expectedResult = new Question(1, "test", Arrays.asList(), 1, "test_type");
        when(questionRepository.getQuestion("test_type")).thenReturn(expectedResult);
        //Act
        Single<Question> actualResult = questionInteractor.getQuestion("test_type");
        //Assert
        actualResult.test().assertResult(expectedResult);
    }

    @Test
    public void initDBTest() {
        // TODO: 13.09.2021
        //Arrange
        //Act
        //Assert
        //Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }
}