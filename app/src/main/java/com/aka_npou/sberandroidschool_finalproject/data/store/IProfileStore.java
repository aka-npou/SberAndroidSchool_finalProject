package com.aka_npou.sberandroidschool_finalproject.data.store;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

/**
 * Интерфейс для работы с профилем игрока
 *
 * @author Мулярчук Александр
 */
public interface IProfileStore {
    /**
     * Сохранение данных профиля игрока
     * @param profile @link Profile} модель профиля игрока из домейн слоя
     */
    void editProfile(Profile profile);

    /**
     * Получение данных профиля игрока
     * @return {@link Profile} модель профиля игрока для домейн слоя
     */
    Profile getProfile();
}
