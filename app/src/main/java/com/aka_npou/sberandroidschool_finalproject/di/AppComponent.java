package com.aka_npou.sberandroidschool_finalproject.di;

import com.aka_npou.sberandroidschool_finalproject.di.activity.ActivityComponent;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    ActivityComponent getActivityComponent();

    IQuestionInteractor getIQuestionInteractor();
    IStatisticInteractor getIStatisticInteractor();
    IProfileInteractor getIProfileInteractor();
}
