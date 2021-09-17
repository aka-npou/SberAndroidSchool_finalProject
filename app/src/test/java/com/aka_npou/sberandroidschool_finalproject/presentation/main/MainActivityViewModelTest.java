package com.aka_npou.sberandroidschool_finalproject.presentation.main;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    IQuestionInteractor questionInteractor;
    @Mock
    ISchedulersProvider schedulersProvider;

    @Mock
    private Observer<Boolean> progressLiveDataObserver;
    @Mock
    private Observer<Throwable> errorLiveDataObserver;
    @Mock
    private Observer<Boolean> initDBLiveDataObserver;

    private MainActivityViewModel viewModel;

    @Before
    public void setUp() {
        when(schedulersProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulersProvider.ui()).thenReturn(Schedulers.trampoline());

        viewModel = new MainActivityViewModel(questionInteractor, schedulersProvider);

        viewModel.getProgressLiveData().observeForever(progressLiveDataObserver);
        viewModel.getErrorLiveData().observeForever(errorLiveDataObserver);
        viewModel.getInitDBLiveData().observeForever(initDBLiveDataObserver);
    }

    @Test
    public void initDBTest() {
        when(questionInteractor.initDB()).thenReturn(Completable.complete());

        viewModel.initDB();

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, initDBLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(initDBLiveDataObserver).onChanged(true);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }
}