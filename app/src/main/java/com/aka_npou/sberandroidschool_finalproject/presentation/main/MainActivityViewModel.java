package com.aka_npou.sberandroidschool_finalproject.presentation.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.CommonViewModel;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

/**
 * ViewModel общего экрана
 *
 * @author Мулярчук Александр
 */
public class MainActivityViewModel extends CommonViewModel {
    private final IQuestionInteractor questionInteractor;
    private final ISchedulersProvider schedulersProvider;

    private final MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> initDBLiveData = new MutableLiveData<>();

    /**
     * Конструктор
     *
     * @param questionInteractor интерактор для получения данных о вопросах
     * @param schedulersProvider провайдер потоков выполнения
     */
    public MainActivityViewModel(IQuestionInteractor questionInteractor,
                                 ISchedulersProvider schedulersProvider) {
        this.questionInteractor = questionInteractor;
        this.schedulersProvider = schedulersProvider;
    }

    /**
     * Инициализация базы данных
     */
    public void initDB() {
        addDisposable(questionInteractor.initDB()
                .doOnSubscribe(disposable -> progressLiveData.postValue(true))
                .doAfterTerminate(() -> progressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(() -> initDBLiveData.postValue(true), errorLiveData::setValue));
    }

    public LiveData<Boolean> getProgressLiveData() {
        return progressLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<Boolean> getInitDBLiveData() {
        return initDBLiveData;
    }
}
