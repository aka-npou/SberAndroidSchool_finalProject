package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

public interface IConverter<T, R> {

    @NonNull
    R convert(@NonNull T item);

    @NonNull
    T reverse(@NonNull R item);

}
