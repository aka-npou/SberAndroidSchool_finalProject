package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.store.IProfileStore;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *  Имплементация интерфейса {@link IProfileInteractor}
 *
 *  @author Мулярчук Александр
 */
public class ProfileInteractor implements IProfileInteractor {
    private final IProfileStore profileStore;

    /**
     * Конструктор
     * @param profileStore {@link IProfileStore} работает с данными профиля пользователя
     */
    public ProfileInteractor(IProfileStore profileStore) {
        this.profileStore = profileStore;
    }

    @Override
    public Completable editProfile(Profile profile) {
        return Completable.fromCallable(() -> {
            profileStore.editProfile(profile);
            return true;
        });
    }

    @Override
    public Single<Profile> getProfile() {
        return Single.fromCallable(profileStore::getProfile);
    }
}
