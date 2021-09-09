package com.aka_npou.sberandroidschool_finalproject.data.store;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

public interface IProfileStore {
    void editProfile(Profile profile);
    Profile getProfile();
}
