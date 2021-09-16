package com.aka_npou.sberandroidschool_finalproject.domain.repository;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;

import java.util.List;

/**
 * Интерфейс репозитория для получения данных по вопросам и ответам
 *
 * @author Мулярчук Александр
 */
public interface IQuestionRepository {
    /**
     * Получает вопрос с вариантами ответов
     *
     * @param typeQuestions тип вопроса
     * @return {@link Question}
     */
    Question getQuestion(String typeQuestions);

    /**
     * Предзаполняет данные вопросов и ответов
     *
     * @return успешность предзаполнения
     */
    boolean initDB();

    /**
     * Получает типы вопроса
     *
     * @return список {@link List} из {@link String} вариантов вопроса
     */
    List<String> getQuestionTypes();
}
