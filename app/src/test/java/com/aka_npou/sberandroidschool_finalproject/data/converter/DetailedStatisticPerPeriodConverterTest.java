package com.aka_npou.sberandroidschool_finalproject.data.converter;

import com.aka_npou.sberandroidschool_finalproject.data.entity.DetailedStatisticPerPeriodEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

public class DetailedStatisticPerPeriodConverterTest {

    private DetailedStatisticPerPeriodConverter detailedStatisticPerPeriodConverter;

    @Before
    public void setup() {
        detailedStatisticPerPeriodConverter = new DetailedStatisticPerPeriodConverter();
    }

    @Test
    public void convertTest() {
        //Arrange
        DetailedStatisticPerPeriodEntity expectedResult = new DetailedStatisticPerPeriodEntity("test_type",
                100,
                50);
        DetailedStatisticPerPeriod detailedStatisticPerPeriod = new DetailedStatisticPerPeriod("test_type",
                100,
                50);
        //Act
        DetailedStatisticPerPeriodEntity actualResult = detailedStatisticPerPeriodConverter.convert(detailedStatisticPerPeriod);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void convertThrowExceptionTest() {
        //Arrange
        DetailedStatisticPerPeriodEntity expectedResult = new DetailedStatisticPerPeriodEntity("test_type",
                100,
                50);
        DetailedStatisticPerPeriod detailedStatisticPerPeriod = null;
        //Act
        DetailedStatisticPerPeriodEntity actualResult = detailedStatisticPerPeriodConverter.convert(detailedStatisticPerPeriod);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void reverseTest() {
        //Arrange
        DetailedStatisticPerPeriod expectedResult = new DetailedStatisticPerPeriod("test_type",
                100,
                50);
        DetailedStatisticPerPeriodEntity detailedStatisticPerPeriodEntity =
                new DetailedStatisticPerPeriodEntity("test_type",
                        100,
                        50);
        //Act
        DetailedStatisticPerPeriod actualResult = detailedStatisticPerPeriodConverter
                .reverse(detailedStatisticPerPeriodEntity);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void reverseThrowExceptionTest() {
        //Arrange
        DetailedStatisticPerPeriod expectedResult = new DetailedStatisticPerPeriod("test_type",
                100,
                50);
        DetailedStatisticPerPeriodEntity detailedStatisticPerPeriodEntity = null;
        //Act
        DetailedStatisticPerPeriod actualResult = detailedStatisticPerPeriodConverter
                .reverse(detailedStatisticPerPeriodEntity);
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

}