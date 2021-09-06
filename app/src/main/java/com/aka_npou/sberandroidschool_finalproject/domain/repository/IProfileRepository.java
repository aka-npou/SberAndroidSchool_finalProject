package com.aka_npou.sberandroidschool_finalproject.domain.repository;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

public interface IProfileRepository {
    boolean editProfile(Profile profile);
    Profile getProfile();
}
