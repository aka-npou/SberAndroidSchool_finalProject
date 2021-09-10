package com.aka_npou.sberandroidschool_finalproject;

import android.app.Application;
import android.content.Context;

import com.aka_npou.sberandroidschool_finalproject.di.AppComponent;
import com.aka_npou.sberandroidschool_finalproject.di.ContextModule;
import com.aka_npou.sberandroidschool_finalproject.di.DaggerAppComponent;

public class QuizApplication extends Application {

    private AppComponent appComponent;

    public static AppComponent getAppComponent(Context context) {
        return ((QuizApplication) context.getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();
    }
}
