package com.aka_npou.sberandroidschool_finalproject.presentation.statistic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class StatisticViewModel extends ViewModel {
    private final IStatisticInteractor mStatisticInteractor;
    private final ISchedulersProvider mSchedulersProvider;

    private final MutableLiveData<Boolean> mProgressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> mErrorLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DailyStatistics>> mStatisticLiveData = new MutableLiveData<>();

    private Disposable mDisposable;

    public StatisticViewModel(IStatisticInteractor statisticInteractor,
                             ISchedulersProvider schedulersProvider) {
        mStatisticInteractor = statisticInteractor;
        mSchedulersProvider = schedulersProvider;
    }

    public void getStatisticAsyncRx(Date from, Date to) {
        mDisposable = mStatisticInteractor.getStatisticForPeriod(from, to)
                .doOnSubscribe(disposable -> mProgressLiveData.postValue(true))
                .doAfterTerminate(() -> mProgressLiveData.postValue(false))
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .subscribe(mStatisticLiveData::setValue, mErrorLiveData::setValue);
    }


    @Override
    protected void onCleared() {
        super.onCleared();

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    public LiveData<Boolean> getProgressLiveData() {
        return mProgressLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return mErrorLiveData;
    }

    public LiveData<List<DailyStatistics>> getStatisticLiveData() {
        return mStatisticLiveData;
    }
}
