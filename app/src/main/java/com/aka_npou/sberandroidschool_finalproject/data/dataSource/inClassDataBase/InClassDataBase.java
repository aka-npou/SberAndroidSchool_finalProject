package com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class InClassDataBase {
    private List<Question> questionList;
    private List<Statistic> statisticList;

    public InClassDataBase() {
        initDataBase();
    }

    private void initDataBase() {
        questionList = new ArrayList<>();

        questionList.add(new Question(1, "2+2", Arrays.asList("1", "2", "3", "4"), 3));
        questionList.add(new Question(2, "3+3", Arrays.asList("6", "2", "3", "4"), 0));
        questionList.add(new Question(3,"4+4", Arrays.asList("1", "8", "3", "4"), 1));
        questionList.add(new Question(4,"5+5", Arrays.asList("1", "2", "10", "4"), 2));
        questionList.add(new Question(5,"6+6", Arrays.asList("1", "12", "10", "4"), 1));
        questionList.add(new Question(6,"7+7", Arrays.asList("1", "2", "10", "14"), 3));
        questionList.add(new Question(7,"8+8", Arrays.asList("1", "2", "10", "16"), 3));
        questionList.add(new Question(8,"9+9", Arrays.asList("1", "18", "10", "4"), 1));

        statisticList = new ArrayList<>();

    }

    public Question getQuestion() {
        Random random = new Random();
        int questionIndex = random.nextInt(questionList.size() - 1);
        return questionList.get(questionIndex);
    }

    public long addStatistic(Statistic statistic) {
        statisticList.add(statistic);
        return statisticList.size();
    }

    public List<Statistic> getStatisticForPeriod(Date from, Date to) {
        List<Statistic> list = new ArrayList<>();

        for (Statistic statistic:statisticList) {
            if (statistic.getDateOfAnswer().getTime() >= from.getTime() && statistic.getDateOfAnswer().getTime() <= to.getTime()) {
                list.add(new Statistic(statistic.getId(), statistic.getAnswerIndex(), statistic.isCorrectAnswer(), statistic.getDateOfAnswer()));
            }
        }

        return list;
    }
}
