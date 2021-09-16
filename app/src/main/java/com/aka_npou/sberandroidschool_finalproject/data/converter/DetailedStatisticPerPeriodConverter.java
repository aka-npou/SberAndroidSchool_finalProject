package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.DetailedStatisticPerPeriodEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;

/**
 * Имплементация интерфейса {@link IConverter} для конвертации {@link DetailedStatisticPerPeriod}
 * в {@link DetailedStatisticPerPeriodEntity}
 *
 * @author Мулярчук Александр
 */
public class DetailedStatisticPerPeriodConverter implements IConverter<DetailedStatisticPerPeriod, DetailedStatisticPerPeriodEntity> {
    @NonNull
    @Override
    public DetailedStatisticPerPeriodEntity convert(@NonNull DetailedStatisticPerPeriod item) {
        //пока не нужен
        return null;
    }

    @NonNull
    @Override
    public DetailedStatisticPerPeriod reverse(@NonNull DetailedStatisticPerPeriodEntity item) {
        return new DetailedStatisticPerPeriod(item.type, item.countQuestions, item.countCorrectQuestions);
    }
}
