package com.aka_npou.sberandroidschool_finalproject.presentation.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.CommonViewModel;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

/**
 * ViewModel для отображения информации о профиле
 *
 * @author Мулярчук Александр
 */
public class ProfileViewModel extends CommonViewModel {
    private final IProfileInteractor profileInteractor;
    private final ISchedulersProvider schedulersProvider;

    private final MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Profile> profileLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> profileEditLiveData = new MutableLiveData<>();

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

    /**
     * Получение профиля
     */
    public void getProfileData() {
        addDisposable(profileInteractor.getProfile()
                .doOnSubscribe(disposable -> progressLiveData.postValue(true))
                .doAfterTerminate(() -> progressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(profileLiveData::setValue, errorLiveData::setValue));
    }

    /**
     * Сохранение профиля
     * @param profile {@link Profile} модель профиля
     */
    public void saveProfile(Profile profile) {
        addDisposable(profileInteractor.editProfile(profile)
                .doOnSubscribe(disposable -> progressLiveData.postValue(true))
                .doAfterTerminate(() -> progressLiveData.postValue(false))
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(() -> profileEditLiveData.postValue(true), errorLiveData::setValue));
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
