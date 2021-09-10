package com.aka_npou.sberandroidschool_finalproject.presentation.common;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Реализация интерфейса по предоставлению потоков выполнения
 *
 * @author Мулярчук Александр
 */
public class SchedulersProvider implements ISchedulersProvider {

    @Inject
    public SchedulersProvider() {
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
