package com.aka_npou.sberandroidschool_finalproject;

import android.app.Application;
import android.content.Context;

import com.aka_npou.sberandroidschool_finalproject.di.AppComponent;
import com.aka_npou.sberandroidschool_finalproject.di.ContextModule;
import com.aka_npou.sberandroidschool_finalproject.di.DaggerAppComponent;

/**
 * Приложение
 *
 * @author Мулярчук Александр
 */
public class QuizApplication extends Application {

    private AppComponent appComponent;

    /**
     * Получение компоненты приложения {@link AppComponent} Dagger2
     * @param context контекст приложения
     * @return {@link AppComponent} компонент Dagger2
     */
    public static AppComponent getAppComponent(Context context) {
        return ((QuizApplication) context.getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();
    }
}
