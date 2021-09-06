package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IProfileRepository;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ProfileInteractor implements IProfileInteractor {
    private final IProfileRepository profileRepository;

    public ProfileInteractor(IProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Completable editProfile(Profile profile) {
        return Completable.fromCallable(() -> profileRepository.editProfile(profile));
    }

    @Override
    public Single<Profile> getProfile() {
        return Single.fromCallable(profileRepository::getProfile);
    }
}
