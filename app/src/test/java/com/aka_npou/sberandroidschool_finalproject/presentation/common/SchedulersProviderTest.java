package com.aka_npou.sberandroidschool_finalproject.presentation.common;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulersProviderTest {

    SchedulersProvider provider;

    @Before
    public void setup() {
        provider = new SchedulersProvider();
    }

    @Test
    public void ioTest() {
        //Arrange
        Scheduler expectedResult = Schedulers.io();
        //Act
        Scheduler actualResult = provider.io();
        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void uiTest() {
        // TODO: 13.09.2021
        //Arrange
        //Act
        //Assert
        //Truth.assertThat(actualResult).isEqualTo(expectedResult);
    }

}