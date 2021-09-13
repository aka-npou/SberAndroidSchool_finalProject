package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.Objects;

/**
 * Модель для отображения пользователю профиля
 *
 * @author Мулярчук Александр
 */
public class Profile {
    private final String name;
    private final String imageFilePath;

    /**
     * Конструктор
     * @param name имя пользователя
     * @param imageFilePath путь до аватарки пользователя вида {@link android.net.Uri}
     */
    public Profile(String name, String imageFilePath) {
        this.name = name;
        this.imageFilePath = imageFilePath;
    }

    public String getName() {
        return name;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(name, profile.name) && Objects.equals(imageFilePath, profile.imageFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageFilePath);
    }
}
