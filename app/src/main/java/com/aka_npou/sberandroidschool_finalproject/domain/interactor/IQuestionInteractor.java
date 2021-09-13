package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Интерактор для получения информации о вопроса и ответах
 *
 * @author Мулярчук Александр
 */
public interface IQuestionInteractor {
        /**
         * Создает задачу по получению вопроса
         * @return {@link Single} RxJava объект выполения задачи по получению вопроса
         */
        Single<Question> getQuestion();

        /**
         * Создает задачу по инициализации базы данных вопросами и ответами
         * @return {@link Completable} RxJava объект выполения задачи по инициализации базы данных
         */
        Completable initDB();
}
