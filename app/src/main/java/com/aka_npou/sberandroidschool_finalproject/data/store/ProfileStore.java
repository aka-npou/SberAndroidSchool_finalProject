package com.aka_npou.sberandroidschool_finalproject.data.store;

import android.content.SharedPreferences;
import android.util.Log;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;
import com.aka_npou.sberandroidschool_finalproject.domain.store.IProfileStore;

/**
 *  Имплементация интерфейса {@link IProfileStore}
 *
 *  @author Мулярчук Александр
 */
public class ProfileStore implements IProfileStore{
    private final static String NAME_KEY = "NAME_KEY";
    private final static String IMAGE_FILE_PATH_KEY = "IMAGE_FILE_PATH_KEY";

    private final SharedPreferences sharedPreferences;

    /**
     * Конструктор
     * @param sharedPreferences {@link SharedPreferences} настройки, куда сохраняется профиль игрока
     */
    public ProfileStore(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void editProfile(Profile profile) {
        sharedPreferences
                .edit()
                .putString(NAME_KEY, profile.getName())
                .putString(IMAGE_FILE_PATH_KEY, profile.getImageFilePath())
                .apply();
        Log.i("TAG", "editProfile: " + profile.getName() + " " + profile.getImageFilePath());
    }

    @Override
    public Profile getProfile() {
        String name = sharedPreferences.getString(NAME_KEY, "");
        String imageFilePath = sharedPreferences.getString(IMAGE_FILE_PATH_KEY, "");
        return new Profile(name, imageFilePath);
    }
}
