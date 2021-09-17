package com.aka_npou.sberandroidschool_finalproject.data.converter;

import com.aka_npou.sberandroidschool_finalproject.data.entity.DetailedStatisticPerPeriodEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.TotalStatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;
import com.aka_npou.sberandroidschool_finalproject.domain.model.TotalStatistic;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TotalStatisticConverterTest {

    private TotalStatisticConverter totalStatisticConverter;

    @Before
    public void setup() {
        totalStatisticConverter = new TotalStatisticConverter();
    }

    @Test
    public void reverseTest() {
        //Arrange
        TotalStatistic expectedResult = new TotalStatistic(100,
                50,
                1);
        TotalStatisticEntity totalStatistic = new TotalStatisticEntity(100,
                50,
                new Date().getTime());
        //Act
        TotalStatistic actualResult = totalStatisticConverter.reverse(totalStatistic);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void reverseThrowExceptionTest() {
        //Arrange
        TotalStatistic expectedResult = new TotalStatistic(100,
                50,
                1);
        TotalStatisticEntity totalStatistic = null;
        //Act
        TotalStatistic actualResult = totalStatisticConverter.reverse(totalStatistic);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }
}