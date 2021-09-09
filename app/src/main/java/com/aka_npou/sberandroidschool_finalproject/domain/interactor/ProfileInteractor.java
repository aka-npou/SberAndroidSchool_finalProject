package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.data.store.IProfileStore;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ProfileInteractor implements IProfileInteractor {
    private final IProfileStore profileStore;

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
