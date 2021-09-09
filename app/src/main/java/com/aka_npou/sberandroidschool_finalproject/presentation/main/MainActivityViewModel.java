package com.aka_npou.sberandroidschool_finalproject.presentation.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import io.reactivex.disposables.Disposable;

public class MainActivityViewModel extends ViewModel {
    private final IQuestionInteractor questionInteractor;
    private final ISchedulersProvider schedulersProvider;

    private final MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> initDBLiveData = new MutableLiveData<>();

    private Disposable mDisposable;

    public MainActivityViewModel(IQuestionInteractor questionInteractor,
                            ISchedulersProvider schedulersProvider) {
        this.questionInteractor = questionInteractor;
        this.schedulersProvider = schedulersProvider;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    public void initDB() {
        mDisposable = questionInteractor.initDB()
                .doOnSubscribe(disposable -> progressLiveData.postValue(true))
                .doAfterTerminate(() -> progressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(() -> initDBLiveData.postValue(true), errorLiveData::setValue);
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
