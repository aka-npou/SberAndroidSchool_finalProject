package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.ProfileEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

public class ProfileConverter implements IConverter<Profile, ProfileEntity> {
    @NonNull
    @Override
    public ProfileEntity convert(@NonNull Profile item) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.id = item.getId();
        profileEntity.name = item.getName();
        profileEntity.imageFilePath = item.getImageFilePath();

        return profileEntity;
    }

    @NonNull
    @Override
    public Profile reverse(@NonNull ProfileEntity item) {
        Profile profile = new Profile(item.id, item.name, item.imageFilePath);
        return profile;
    }
}
