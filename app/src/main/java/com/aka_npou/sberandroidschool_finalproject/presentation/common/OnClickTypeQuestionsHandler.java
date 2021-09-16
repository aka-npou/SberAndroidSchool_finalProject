package com.aka_npou.sberandroidschool_finalproject.presentation.common;

/**
 * Интерфейс по старту игры по выбранному типу вопросов
 *
 * @author Мулярчук Александр
 */
public interface OnClickTypeQuestionsHandler {
    /**
     * Стартует игру с выбранным типом вопросов
     *
     * @param typeQuestions выбранный тип вопросов
     */
    void startGame(String typeQuestions);
}
