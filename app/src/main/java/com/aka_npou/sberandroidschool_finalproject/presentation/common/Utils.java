package com.aka_npou.sberandroidschool_finalproject.presentation.common;

public class Utils {
    public static String getDayAddition(int num) {

        int preLastDigit = num % 100 / 10;

        if (preLastDigit == 1) {
            return "дней";
        }

        switch (num % 10) {
            case 1:
                return "день";
            case 2:
            case 3:
            case 4:
                return "дня";
            default:
                return "дней";
        }
    }

    public static String getQuestionAddition(int num) {

        int preLastDigit = num % 100 / 10;

        if (preLastDigit == 1) {
            return "вопросов";
        }

        switch (num % 10) {
            case 1:
                return "вопрос";
            case 2:
            case 3:
            case 4:
                return "вопроса";
            default:
                return "вопросов";
        }
    }
}
