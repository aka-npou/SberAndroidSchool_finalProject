package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;

import java.util.List;

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
     *
     * @param typeQuestions тип вопроса
     * @return {@link Single} RxJava объект выполения задачи по получению вопроса
     */
    Single<Question> getQuestion(String typeQuestions);

    /**
     * Создает задачу по инициализации базы данных вопросами и ответами
     *
     * @return {@link Completable} RxJava объект выполения задачи по инициализации базы данных
     */
    Completable initDB();

    /**
     * Создает задачу по получению типов вопроса
     *
     * @return {@link Single} RxJava объект выполнения задачи по получению типов вопроса
     */
    Single<List<String>> getQuestionTypes();
}
