package com.aka_npou.sberandroidschool_finalproject.data.repository;

import static org.mockito.Mockito.when;

import com.aka_npou.sberandroidschool_finalproject.data.converter.DetailedStatisticPerPeriodConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.StatisticConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.TotalStatisticConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StatisticRepositoryTest {

    @Mock
    StatisticConverter converter;

    @Mock
    TotalStatisticConverter totalStatisticConverter;

    @Mock
    DetailedStatisticPerPeriodConverter detailedStatisticPerPeriodConverter;

    @Mock
    IStatisticDao statisticDao;


    private IStatisticRepository repository;

    @Before
    public void setup() {
        repository = new StatisticRepository(statisticDao,
                converter,
                totalStatisticConverter,
                detailedStatisticPerPeriodConverter);
    }

    @Test
    public void addAnswerResultTest() {
        //Arrange
        //Act
        //Assert
        //Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void getStatisticForPeriodTest() {
        //Arrange
        long date1 = 1_000_001;
        long date2 = 1_000_002;

        List<Statistic> expectedResult = Arrays.asList(new Statistic(1,1,1,true, new Date(date1)),
                                                       new Statistic(2,1,1,true, new Date(date2)));

        List<StatisticEntity> statisticEntityList = Arrays.asList(new StatisticEntity(1,1,1,true,new Date(date1).getTime()),
                                                                  new StatisticEntity(2,1,1,true,new Date(date2).getTime()));
        when(statisticDao.getStatisticForPeriod(date1, date2)).thenReturn(statisticEntityList);

        when(converter.reverse(statisticEntityList.get(0))).thenReturn(expectedResult.get(0));
        when(converter.reverse(statisticEntityList.get(1))).thenReturn(expectedResult.get(1));
        //Act
        List<Statistic> actualResult = repository.getStatisticForPeriod(new Date(date1), new Date(date2));
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

}