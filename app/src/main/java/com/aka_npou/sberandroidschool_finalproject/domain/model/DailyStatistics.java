package com.aka_npou.sberandroidschool_finalproject.domain.model;

import java.util.Date;

public class DailyStatistics {
    private final Date mDateOfAnswer;
    private int mCountQuestions;
    private int mCountCorrectQuestions;

    public DailyStatistics(Date mDateOfAnswer) {
        this.mDateOfAnswer = mDateOfAnswer;
        mCountQuestions = 0;
        mCountCorrectQuestions = 0;
    }

    public DailyStatistics(Date dateOfAnswer, int countQuestions, int countCorrectQuestions) {
        mDateOfAnswer = dateOfAnswer;
        mCountQuestions = countQuestions;
        mCountCorrectQuestions = countCorrectQuestions;
    }

    public Date getDateOfAnswer() {
        return mDateOfAnswer;
    }

    public int getCountQuestions() {
        return mCountQuestions;
    }

    public int getCountCorrectQuestions() {
        return mCountCorrectQuestions;
    }

    public void setCountQuestions(int countQuestions) {
        mCountQuestions = countQuestions;
    }

    public void setCountCorrectQuestions(int countCorrectQuestions) {
        mCountCorrectQuestions = countCorrectQuestions;
    }
}
