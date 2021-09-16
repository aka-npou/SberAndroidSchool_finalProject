package com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeQuestions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * ViewModel для отображения типов вопросов
 *
 * @author Мулярчук Александр
 */
public class SelectTypeQuestionsFragmentViewModel extends ViewModel {
    private final IQuestionInteractor questionInteractor;
    private final ISchedulersProvider schedulersProvider;

    private final MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<String>> questionTypeData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();

    private Disposable disposable;

    /**
     * Конструктор
     *
     * @param questionInteractor интерактор для получения типов вопросов
     * @param schedulersProvider провайдер потоков выполнения
     */
    public SelectTypeQuestionsFragmentViewModel(IQuestionInteractor questionInteractor,
                                                ISchedulersProvider schedulersProvider) {
        this.questionInteractor = questionInteractor;
        this.schedulersProvider = schedulersProvider;
    }

    /**
     * Получение типов вопроса
     */
    public void getQuestionTypes() {
        disposable = questionInteractor.getQuestionTypes()
                .doOnSubscribe(disposable -> progressLiveData.postValue(true))
                .doAfterTerminate(() -> progressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(questionTypeData::setValue, errorLiveData::setValue);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    public LiveData<Boolean> getProgressLiveData() {
        return progressLiveData;
    }

    public LiveData<List<String>> getQuestionTypeData() {
        return questionTypeData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }
}
