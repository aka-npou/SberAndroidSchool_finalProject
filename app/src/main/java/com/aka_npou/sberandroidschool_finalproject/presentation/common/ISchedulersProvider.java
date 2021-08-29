package com.aka_npou.sberandroidschool_finalproject.presentation.common;

import io.reactivex.Scheduler;

/**
 * Интерфейс по предоставлению потоков выполнения
 *
 * @author Мулярчук Александр
 */
public interface ISchedulersProvider {

    /**
     * Метод для получения потока ввода вывода
     *
     * @return поток для ввода вывода
     */
    Scheduler io();

    /**
     * Метод для возвращения потока отрисовки
     *
     * @return поток для отрисовки
     */
    Scheduler ui();
}
