package com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IProfileDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.ProfileEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.List;

public class InClassProfileDao implements IProfileDao {

    private final InClassDataBase inClassDataBase;

    public InClassProfileDao(InClassDataBase inClassDataBase) {
        this.inClassDataBase = inClassDataBase;
    }

    @Override
    public boolean editProfile(ProfileEntity entity) {
        return inClassDataBase.editProfile(entity);
    }

    @Override
    public ProfileEntity getProfile() {
        return inClassDataBase.getProfile();
    }
}
