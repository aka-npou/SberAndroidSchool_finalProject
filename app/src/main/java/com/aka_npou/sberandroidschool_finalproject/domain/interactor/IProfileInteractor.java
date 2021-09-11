package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Profile;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Интерактор для получения информации о профиле игрока
 *
 * @author Мулярчук Александр
 */
public interface IProfileInteractor {
    /**
     * Создает задачу по изменению профиля игрока
     * @param profile {@link Profile} модель профиля игрока
     * @return {@link Completable} RxJava объект выполения задачи по изменению профиля
     */
    Completable editProfile(Profile profile);

    /**
     * Создает задачу по получению профиля игрока
     * @return {@link Single} RxJava объект выполнения задачи по получению профиля
     */
    Single<Profile> getProfile();
}
