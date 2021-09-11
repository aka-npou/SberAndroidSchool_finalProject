package com.aka_npou.sberandroidschool_finalproject.domain.model;

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
}
