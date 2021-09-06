package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import com.aka_npou.sberandroidschool_finalproject.data.entity.ProfileEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.List;

public interface IProfileDao {
    boolean editProfile(ProfileEntity entity);
    ProfileEntity getProfile();
}
