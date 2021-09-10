package com.aka_npou.sberandroidschool_finalproject.di.activity;


import com.aka_npou.sberandroidschool_finalproject.presentation.ViewModelFactory;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    ViewModelFactory getViewModelFactory();
}
