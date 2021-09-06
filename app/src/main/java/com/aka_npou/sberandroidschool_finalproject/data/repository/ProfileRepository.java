package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IProfileDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.ProfileEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IProfileRepository;

public class ProfileRepository implements IProfileRepository {
    private final IProfileDao profileDao;
    private final IConverter<Profile, ProfileEntity> converter;

    public ProfileRepository(IProfileDao profileDao, IConverter<Profile, ProfileEntity> converter) {
        this.profileDao = profileDao;
        this.converter = converter;
    }

    @Override
    public boolean editProfile(Profile profile) {
        return profileDao.editProfile(converter.convert(profile));
    }

    @Override
    public Profile getProfile() {
        return converter.reverse(profileDao.getProfile());
    }
}
