package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;

public class StatisticConverter implements IConverter<Statistic, StatisticEntity> {
    @NonNull
    @Override
    public StatisticEntity convert(@NonNull Statistic item) {
        return null;
    }

    @NonNull
    @Override
    public Statistic reverse(@NonNull StatisticEntity item) {
        return null;
    }
}
