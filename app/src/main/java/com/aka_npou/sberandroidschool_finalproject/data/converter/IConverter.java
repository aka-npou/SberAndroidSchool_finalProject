package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

/**
 * Интерфейс для конвертации/инвертации одного объекта в другой
 *
 * @param <T> класс объекта
 * @param <R> класс объекта
 *
 * @author Мулярчук Александр
 */
public interface IConverter<T, R> {

    /**
     *
     * @param item объект который надо сконвертировать в <R>
     * @return <R>
     */
    @NonNull
    R convert(@NonNull T item);

    /**
     *
     * @param item объект который надо инвертировать в <T>
     * @return <T>
     */
    @NonNull
    T reverse(@NonNull R item);

}
