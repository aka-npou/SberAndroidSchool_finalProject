package com.aka_npou.sberandroidschool_finalproject.presentation.statistic;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;
import com.aka_npou.sberandroidschool_finalproject.domain.model.TotalStatistic;
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
    @Mock
    private Observer<TotalStatistic> totalStatisticLiveDataObserver;
    @Mock
    private Observer<List<DetailedStatisticPerPeriod>> explicitDayStatisticLiveDataObserver;

    private StatisticViewModel viewModel;

    @Before
    public void setUp() {
        when(schedulersProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulersProvider.ui()).thenReturn(Schedulers.trampoline());

        viewModel = new StatisticViewModel(statisticInteractor, schedulersProvider);

        viewModel.getProgressLiveData().observeForever(progressLiveDataObserver);
        viewModel.getErrorLiveData().observeForever(errorLiveDataObserver);
        viewModel.getStatisticLiveData().observeForever(statisticLiveDataObserver);
        viewModel.getTotalStatisticLiveData().observeForever(totalStatisticLiveDataObserver);
        viewModel.getExpListLiveData().observeForever(explicitDayStatisticLiveDataObserver);
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

    @Test
    public void getTotalStatisticTest() {
        TotalStatistic totalStatistic = new TotalStatistic(100, 50, 1);

        when(statisticInteractor.getTotalStatistic()).thenReturn(Single.just(totalStatistic));

        viewModel.getTotalStatistic();

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, totalStatisticLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(totalStatisticLiveDataObserver).onChanged(totalStatistic);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getTotalStatisticTestError() {
        Exception exception = new Exception("Test");

        when(statisticInteractor.getTotalStatistic()).thenReturn(Single.error(exception));

        viewModel.getTotalStatistic();

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, errorLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(errorLiveDataObserver).onChanged(exception);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getExplicitStatisticForPeriod() {
        Date date = new Date(1_000_001);
        Date date1 = new Date(-10800000);
        Date date2 = new Date(75599999);

        List<DetailedStatisticPerPeriod> list = Arrays.asList(
                new DetailedStatisticPerPeriod("test_type1", 100, 50),
                new DetailedStatisticPerPeriod("test_type2", 200, 10));

        when(statisticInteractor.getExplicitStatisticForPeriod(date1, date2)).thenReturn(Single.just(list));

        viewModel.getExplicitStatisticForPeriod(date);

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, explicitDayStatisticLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(explicitDayStatisticLiveDataObserver).onChanged(list);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getExplicitStatisticForPeriodError() {
        Date date = new Date(1_000_001);
        Date date1 = new Date(-10800000);
        Date date2 = new Date(75599999);

        Exception exception = new Exception("Test");
        when(statisticInteractor.getExplicitStatisticForPeriod(date1, date2)).thenReturn(Single.error(exception));

        viewModel.getExplicitStatisticForPeriod(date);

        InOrder inOrder = Mockito.inOrder(progressLiveDataObserver, errorLiveDataObserver);

        //Проверка, что презентер действительно вызывает методы представления, причем в порядке вызова этих методов.
        inOrder.verify(progressLiveDataObserver).onChanged(true);
        inOrder.verify(errorLiveDataObserver).onChanged(exception);
        inOrder.verify(progressLiveDataObserver).onChanged(false);

        //Проверка, что никакой метод не будет вызван.
        inOrder.verifyNoMoreInteractions();
    }
}