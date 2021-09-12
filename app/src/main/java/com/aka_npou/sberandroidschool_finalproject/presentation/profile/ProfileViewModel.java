package com.aka_npou.sberandroidschool_finalproject.presentation.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import io.reactivex.disposables.Disposable;

/**
 * ViewModel для отображения информации о профиле
 *
 * @author Мулярчук Александр
 */
public class ProfileViewModel extends ViewModel {
    private final IProfileInteractor profileInteractor;
    private final ISchedulersProvider schedulersProvider;

    private final MutableLiveData<Boolean> mProgressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> mErrorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Profile> profileLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> profileEditLiveData = new MutableLiveData<>();

    private Disposable mDisposable;

    /**
     * Конструктор
     * @param profileInteractor интерактор для получения данных профиля
     * @param schedulersProvider провайдер потоков выполнения
     */
    public ProfileViewModel(IProfileInteractor profileInteractor,
                            ISchedulersProvider schedulersProvider) {
        this.profileInteractor = profileInteractor;
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

    /**
     * Получение профиля
     */
    public void getProfileData() {
        mDisposable = profileInteractor.getProfile()
                .doOnSubscribe(disposable -> mProgressLiveData.postValue(true))
                .doAfterTerminate(() -> mProgressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(profileLiveData::setValue, mErrorLiveData::setValue);
    }

    /**
     * Сохранение профиля
     * @param profile {@link Profile} модель профиля
     */
    public void saveProfile(Profile profile) {
        mDisposable = profileInteractor.editProfile(profile)
                .doOnSubscribe(disposable -> mProgressLiveData.postValue(true))
                .doAfterTerminate(() -> mProgressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(() -> profileEditLiveData.postValue(true), mErrorLiveData::setValue);
    }

    public LiveData<Boolean> getProgressLiveData() {
        return mProgressLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return mErrorLiveData;
    }

    public LiveData<Profile> getProfileLiveData() {
        return profileLiveData;
    }

    public LiveData<Boolean> getProfileEditLiveData() {
        return profileEditLiveData;
    }
}
