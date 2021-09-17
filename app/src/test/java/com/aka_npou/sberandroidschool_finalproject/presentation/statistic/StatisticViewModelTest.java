package com.aka_npou.sberandroidschool_finalproject.presentation.statistic;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class StatisticViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    IStatisticInteractor statisticInteractor;
    @Mock
    ISchedulersProvider schedulersProvider;

    @Mock
    private Observer<Boolean> progressLiveDataObserver;
    @Mock
    private Observer<Throwable> errorLiveDataObserver;
    @Mock
    private Observer<List<DailyStatistics>> statisticLiveDataObserver;

    private StatisticViewModel viewModel;

    @Before
    public void setUp() {
        when(schedulersProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulersProvider.ui()).thenReturn(Schedulers.trampoline());

        viewModel = new StatisticViewModel(statisticInteractor, schedulersProvider);

        viewModel.getProgressLiveData().observeForever(progressLiveDataObserver);
        viewModel.getErrorLiveData().observeForever(errorLiveDataObserver);
        viewModel.getStatisticLiveData().observeForever(statisticLiveDataObserver);
    }

    @Test
    public void getStatisticForPeriodTest() {
        Date date1 = new Date(1_000_001);
        Date date2 = new Date(1_000_002);

        List<DailyStatistics> dailyStatisticsList = Arrays.asList(new DailyStatistics(date1, 1,1),
                new DailyStatistics(date2, 1,1));

        when(statisticInteractor.getStatisticForPeriod(date1, date2)).thenReturn(Single.just(dailyStatisticsList));

        viewModel.getStatisticForPeriod(date1, date2);

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, statisticLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(statisticLiveDataObserver).onChanged(dailyStatisticsList);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getStatisticForPeriodTestError() {
        Date date1 = new Date(1_000_001);
        Date date2 = new Date(1_000_002);

        Exception exception = new Exception("Test");
        when(statisticInteractor.getStatisticForPeriod(date1, date2)).thenReturn(Single.error(exception));

        viewModel.getStatisticForPeriod(date1, date2);

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, errorLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(errorLiveDataObserver).onChanged(exception);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }
}