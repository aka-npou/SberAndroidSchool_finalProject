package com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase;

import com.aka_npou.sberandroidschool_finalproject.data.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InClassDataBase {
    private List<Question> questionList;

    public InClassDataBase() {
        initDataBase();
    }

    private void initDataBase() {
        questionList = new ArrayList<>();

        questionList.add(new Question("2+2", Arrays.asList("1", "2", "3", "4"), 3));
        questionList.add(new Question("3+3", Arrays.asList("6", "2", "3", "4"), 0));
        questionList.add(new Question("4+4", Arrays.asList("1", "8", "3", "4"), 1));
        questionList.add(new Question("5+5", Arrays.asList("1", "2", "10", "4"), 2));
        questionList.add(new Question("6+6", Arrays.asList("1", "12", "10", "4"), 1));
        questionList.add(new Question("7+7", Arrays.asList("1", "2", "10", "14"), 3));
        questionList.add(new Question("8+8", Arrays.asList("1", "2", "10", "16"), 3));
        questionList.add(new Question("9+9", Arrays.asList("1", "18", "10", "4"), 1));
    }

    public Question getQuestion() {
        Random random = new Random();
        int questionIndex = random.nextInt(questionList.size() - 1);
        return questionList.get(questionIndex);
    }
}
