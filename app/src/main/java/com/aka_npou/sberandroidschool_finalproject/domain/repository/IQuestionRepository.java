package com.aka_npou.sberandroidschool_finalproject.domain.repository;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;

/**
 * Интерфейс репозитория для получения данных по вопросам и ответам
 *
 * @author Мулярчук Александр
 */
public interface IQuestionRepository {
    /**
     * Получает вопрос с вариантами ответов
     * @return {@link Question}
     */
    Question getQuestion();

    /**
     * Предзаполняет данные вопросов и ответов
     * @return успешность предзаполнения
     */
    boolean initDB();
}
