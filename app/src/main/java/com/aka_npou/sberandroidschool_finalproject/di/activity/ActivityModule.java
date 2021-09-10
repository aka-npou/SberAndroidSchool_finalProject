package com.aka_npou.sberandroidschool_finalproject.di.activity;


import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.ViewModelFactory;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.SchedulersProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public interface ActivityModule {

    @ActivityScope
    @Provides
    static ViewModelFactory provideViewModelFactory(IQuestionInteractor questionInteractor,
                                                    IStatisticInteractor statisticInteractor,
                                                    IProfileInteractor profileInteractor,
                                                    ISchedulersProvider schedulersProvider) {
        return new ViewModelFactory(questionInteractor, statisticInteractor, profileInteractor, schedulersProvider);
    }

    @Binds
    ISchedulersProvider bindsISchedulersProvider(SchedulersProvider schedulersProvider);
}
