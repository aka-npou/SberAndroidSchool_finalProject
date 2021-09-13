package com.aka_npou.sberandroidschool_finalproject.presentation.question;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.profile.ProfileViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class QuestionViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    IQuestionInteractor questionInteractor;
    @Mock
    IStatisticInteractor statisticInteractor;
    @Mock
    ISchedulersProvider schedulersProvider;

    @Mock
    private Observer<Question> questionLiveDataObserver;
    @Mock
    private Observer<Boolean> statisticLiveDataObserver;

    private QuestionViewModel viewModel;

    @Before
    public void setUp() {
        when(schedulersProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulersProvider.ui()).thenReturn(Schedulers.trampoline());

        viewModel = new QuestionViewModel(questionInteractor, statisticInteractor, schedulersProvider);

        viewModel.getQuestionLiveData().observeForever(questionLiveDataObserver);
        viewModel.getStatisticLiveData().observeForever(statisticLiveDataObserver);
    }

    @Test
    public void getQuestionTest() {
        // TODO: 13.09.2021 в пачке тестов не проходит
        Question question = new Question(1, "test", new ArrayList<>(), 1);
        when(questionInteractor.getQuestion()).thenReturn(Single.just(question));

        viewModel.getQuestion(0);

        InOrder inOrder = Mockito.inOrder(questionLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(questionLiveDataObserver).onChanged(question);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void addAnswerResultTest() {
        long questionId = 1;
        int answerIndex = 1;
        final boolean isCorrectAnswer = true;
        Date dateOfAnswer = new Date(1_000_001);

        when(statisticInteractor.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer)).thenReturn(Completable.complete());

        viewModel.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer);

        InOrder inOrder = Mockito.inOrder(statisticLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(statisticLiveDataObserver).onChanged(true);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void addAnswerResultTestError() {
        Exception exception = new Exception("Test");
        long questionId = 1;
        int answerIndex = 1;
        final boolean isCorrectAnswer = true;
        Date dateOfAnswer = new Date(1_000_001);

        when(statisticInteractor.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer)).thenReturn(Completable.error(exception));

        viewModel.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer);

        InOrder inOrder = Mockito.inOrder(statisticLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(statisticLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }
}