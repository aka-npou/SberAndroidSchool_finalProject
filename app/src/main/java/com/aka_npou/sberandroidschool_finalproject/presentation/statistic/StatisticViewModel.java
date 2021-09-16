package com.aka_npou.sberandroidschool_finalproject.presentation.statistic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.CommonViewModel;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import java.util.Date;
import java.util.List;

/**
 * ViewModel для отображения статистики
 *
 * @author Мулярчук Александр
 */
public class StatisticViewModel extends CommonViewModel {
    private final IStatisticInteractor statisticInteractor;
    private final ISchedulersProvider schedulersProvider;

    private final MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DailyStatistics>> statisticLiveData = new MutableLiveData<>();

    /**
     * Конструктор
     * @param statisticInteractor интерактор для получения данных по статистике
     * @param schedulersProvider провайдер потоков выполнения
     */
    public StatisticViewModel(IStatisticInteractor statisticInteractor,
                             ISchedulersProvider schedulersProvider) {
        this.statisticInteractor = statisticInteractor;
        this.schedulersProvider = schedulersProvider;
    }

    /**
     * Получение статистики по результатам ответов за период
     * @param from дата с которой получать статистику
     * @param to дата по которую получать статистику
     */
    public void getStatisticAsyncRx(Date from, Date to) {
        addDisposable(statisticInteractor.getStatisticForPeriod(from, to)
                .doOnSubscribe(disposable -> progressLiveData.postValue(true))
                .doAfterTerminate(() -> progressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(statisticLiveData::setValue, errorLiveData::setValue));
    }

    public LiveData<Boolean> getProgressLiveData() {
        return progressLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<List<DailyStatistics>> getStatisticLiveData() {
        return statisticLiveData;
    }
}
