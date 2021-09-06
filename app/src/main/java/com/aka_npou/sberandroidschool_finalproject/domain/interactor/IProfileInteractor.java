package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IProfileInteractor {
    Completable editProfile(Profile profile);
    Single<Profile> getProfile();
}
