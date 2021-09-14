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

    private final MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Profile> profileLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> profileEditLiveData = new MutableLiveData<>();

    private Disposable disposable;

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

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    /**
     * Получение профиля
     */
    public void getProfileData() {
        disposable = profileInteractor.getProfile()
                .doOnSubscribe(disposable -> progressLiveData.postValue(true))
                .doAfterTerminate(() -> progressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(profileLiveData::setValue, errorLiveData::setValue);
    }

    /**
     * Сохранение профиля
     * @param profile {@link Profile} модель профиля
     */
    public void saveProfile(Profile profile) {
        disposable = profileInteractor.editProfile(profile)
                .doOnSubscribe(disposable -> progressLiveData.postValue(true))
                .doAfterTerminate(() -> progressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(() -> profileEditLiveData.postValue(true), errorLiveData::setValue);
    }

    public LiveData<Boolean> getProgressLiveData() {
        return progressLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<Profile> getProfileLiveData() {
        return profileLiveData;
    }

    public LiveData<Boolean> getProfileEditLiveData() {
        return profileEditLiveData;
    }
}
