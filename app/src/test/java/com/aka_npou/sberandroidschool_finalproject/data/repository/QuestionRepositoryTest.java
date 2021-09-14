package com.aka_npou.sberandroidschool_finalproject.data.repository;

import static org.mockito.Mockito.when;

import com.aka_npou.sberandroidschool_finalproject.data.converter.QuestionWithAnswersAndTypeConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswersAndType;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class QuestionRepositoryTest {

    @Mock
    QuestionWithAnswersAndTypeConverter converter;

    @Mock
    IQuestionDao questionDao;


    private IQuestionRepository repository;

    @Before
    public void setup() {
        repository = new QuestionRepository(questionDao, converter);
    }

    @Test
    public void getQuestionTest() {
        //Arrange
        Question expectedResult = new Question(1, "test", new ArrayList<>(), 1, "test_type");
        Question question = new Question(1, "test", new ArrayList<>(), 1, "test_type");

        QuestionWithAnswersAndType questionWithAnswers = new QuestionWithAnswersAndType();
        questionWithAnswers.answers = Arrays.asList(new AnswerEntity(0, "test answer1", 1), new AnswerEntity(0, "test answer2", 1));
        questionWithAnswers.questionEntity = new QuestionEntity(1, "test", 1);
        when(questionDao.getUncommonQuestion("test_type")).thenReturn(questionWithAnswers);

        when(converter.reverse(questionWithAnswers)).thenReturn(question);
        //Act
        Question actualResult = repository.getQuestion("test_type");
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }


}