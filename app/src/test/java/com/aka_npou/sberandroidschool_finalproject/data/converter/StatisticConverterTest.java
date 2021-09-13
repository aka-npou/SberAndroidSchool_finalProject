package com.aka_npou.sberandroidschool_finalproject.data.converter;

import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class StatisticConverterTest {

    private StatisticConverter statisticConverter;

    @Before
    public void setup() {
        statisticConverter = new StatisticConverter();
    }

    @Test
    public void convertQuestionTest() {
        //Arrange
        StatisticEntity expectedResult = new StatisticEntity(1, 1, 1, true, new Date(1_000_000).getTime());
        Statistic statistic = new Statistic(1, 1, 1, true, new Date(1_000_000));
        //Act
        StatisticEntity actualResult = statisticConverter.convert(statistic);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void convertQuestionThrowExceptionTest() {
        //Arrange
        StatisticEntity expectedResult = new StatisticEntity(1, 1, 1, true, new Date(1_000_000).getTime());
        Statistic statistic = null;
        //Act
        StatisticEntity actualResult = statisticConverter.convert(statistic);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void reverseQuestionTest() {
        //Arrange
        Statistic expectedResult = new Statistic(1, 1, 1, true, new Date(1_000_000));;
        StatisticEntity statisticEntity = new StatisticEntity(1, 1, 1, true, new Date(1_000_000).getTime());
        //Act
        Statistic actualResult = statisticConverter.reverse(statisticEntity);
        //Assert
        Truth.assertThat(actualResult).isNotEqualTo(expectedResult);
    }

    @Test
    public void reverseQuestionStartDayv() {
        //Arrange
        Statistic expectedResult = new Statistic(1, 1, 1, true, new Date(-10800000));;
        StatisticEntity statisticEntity = new StatisticEntity(1, 1, 1, true, new Date(-10800000).getTime());
        //Act
        Statistic actualResult = statisticConverter.reverse(statisticEntity);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void reverseQuestionThrowExceptionTest() {
        //Arrange
        Statistic expectedResult = new Statistic(1, 1, 1, true, new Date(1_000_000));;
        StatisticEntity statisticEntity = null;
        //Act
        Statistic actualResult = statisticConverter.reverse(statisticEntity);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

}