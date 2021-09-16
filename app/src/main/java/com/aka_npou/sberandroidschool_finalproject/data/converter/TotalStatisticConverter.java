package com.aka_npou.sberandroidschool_finalproject.data.converter;

import androidx.annotation.NonNull;

import com.aka_npou.sberandroidschool_finalproject.data.entity.TotalStatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.domain.model.TotalStatistic;

import java.util.Date;

/**
 * Имплементация интерфейса {@link IConverter} для конвертации {@link TotalStatistic}
 * в {@link TotalStatisticEntity}
 *
 * @author Мулярчук Александр
 */
public class TotalStatisticConverter implements IConverter<TotalStatistic, TotalStatisticEntity> {
    @NonNull
    @Override
    public TotalStatisticEntity convert(@NonNull TotalStatistic item) {
        //пока не нужен
        return null;
    }

    @NonNull
    @Override
    public TotalStatistic reverse(@NonNull TotalStatisticEntity item) {
        Date entityDate = new Date(item.firstDay);
        Date currentDate = new Date();

        int ms_in_day = 24 * 60 * 60 * 1000;
        //тк int обрезает, то всегда будет округление в меньшую сторону, потому плюс один день
        int days = (int)((currentDate.getTime() - entityDate.getTime()) / ms_in_day) + 1;

        return new TotalStatistic(item.countQuestions, item.countCorrectAnswers, days);
    }
}
