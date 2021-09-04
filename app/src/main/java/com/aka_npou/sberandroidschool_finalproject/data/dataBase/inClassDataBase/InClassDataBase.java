package com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class InClassDataBase {
    private List<QuestionEntity> questionList;
    private List<StatisticEntity> statisticList;

    public InClassDataBase() {
        initDataBase();
    }

    private void initDataBase() {
        questionList = new ArrayList<>();

        questionList.add(new QuestionEntity(1, "2+2", Arrays.asList("1", "2", "3", "4"), 3));
        questionList.add(new QuestionEntity(2, "3+3", Arrays.asList("6", "2", "3", "4"), 0));
        questionList.add(new QuestionEntity(3, "4+4", Arrays.asList("1", "8", "3", "4"), 1));
        questionList.add(new QuestionEntity(4, "5+5", Arrays.asList("1", "2", "10", "4"), 2));
        questionList.add(new QuestionEntity(5, "6+6", Arrays.asList("1", "12", "10", "4"), 1));
        questionList.add(new QuestionEntity(6, "7+7", Arrays.asList("1", "2", "10", "14"), 3));
        questionList.add(new QuestionEntity(7, "8+8", Arrays.asList("1", "2", "10", "16"), 3));
        questionList.add(new QuestionEntity(8, "9+9", Arrays.asList("1", "18", "10", "4"), 1));


        statisticList = new ArrayList<>();

        Calendar calendar;
        calendar = new GregorianCalendar(2021, 0, 1);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));

        calendar = new GregorianCalendar(2021, 0, 5);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));

        calendar = new GregorianCalendar(2021, 0, 10);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));

        calendar = new GregorianCalendar(2021, 0, 11);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));

        calendar = new GregorianCalendar(2021, 0, 12);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));

        calendar = new GregorianCalendar(2021, 0, 13);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -13);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -10);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        statisticList.add(new StatisticEntity(1, 1, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(2, 2, 1, false, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));
        statisticList.add(new StatisticEntity(3, 3, 1, true, calendar.getTimeInMillis()));
    }

    public QuestionEntity getQuestion() {
        Random random = new Random();
        int questionIndex = random.nextInt(questionList.size() - 1);
        return questionList.get(questionIndex);
    }

    public boolean addStatistic(StatisticEntity statistic) {
        statisticList.add(statistic);
        return true;
    }

    public List<StatisticEntity> getStatisticForPeriod(long from, long to) {
        List<StatisticEntity> list = new ArrayList<>();

        for (StatisticEntity entity : statisticList) {
            if (entity.dateOfAnswer >= from && entity.dateOfAnswer <= to) {
                list.add(new StatisticEntity(entity.id, entity.questionId, entity.answerIndex, entity.isCorrectAnswer, entity.dateOfAnswer));
            }
        }

        return list;
    }
}
