package com.aka_npou.sberandroidschool_finalproject.presentation.statistic;

import java.util.Date;

/**
 * Интерфейс для передачи выбранного дня
 *
 * @author Мулярчук Александр
 */
public interface OnClickDayStatisticHandler {
    /**
     * Передает выбранный день статистики
     *
     * @param date {@link Date} выбранная дата статистики
     */
    void viewDayStatistic(Date date);
}
