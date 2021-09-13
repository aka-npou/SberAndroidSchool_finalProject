package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import static org.mockito.Mockito.when;

import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.reactivex.Single;

@RunWith(MockitoJUnitRunner.class)
public class StatisticInteractorTest {

    @Mock
    IStatisticRepository statisticRepository;

    IStatisticInteractor statisticInteractor;

    @Before
    public void setup() {
        statisticInteractor = new StatisticInteractor(statisticRepository);
    }

    @Test
    public void addAnswerResultTest() {
        // TODO: 13.09.2021
        //Arrange
        //Act
        //Assert
        //Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void getStatisticForPeriodTest() {
        //Arrange
        Date date1 = new Date(1_000_001);
        Date date2 = new Date(1_000_002);
        List<Statistic> statisticList = Arrays.asList(new Statistic(1,1,1,true, date1),
                                                       new Statistic(1,1,1,true, date2));

        List<DailyStatistics> expectedResult = Arrays.asList(new DailyStatistics(date1, 1,1),
                                                             new DailyStatistics(date2, 1,1));
        when(statisticRepository.getStatisticForPeriod(date1, date2)).thenReturn(statisticList);
        //Act
        Single<List<DailyStatistics>> actualResult = statisticInteractor.getStatisticForPeriod(date1, date2);
        //Assert
        actualResult.test().assertResult(expectedResult);
    }
}