package com.aka_npou.sberandroidschool_finalproject.presentation.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.CommonViewModel;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * ViewModel для отображения вопроса
 *
 * @author Мулярчук Александр
 */
public class QuestionViewModel extends CommonViewModel {
    private final IQuestionInteractor questionInteractor;
    private final IStatisticInteractor statisticInteractor;
    private final ISchedulersProvider schedulersProvider;

    private final MutableLiveData<Question> questionLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> statisticLiveData = new MutableLiveData<>();

    /**
     * Конструктор
     *
     * @param questionInteractor  интерактор для получения вопросов и ответов
     * @param statisticInteractor интерактор для отправки результата ответа
     * @param schedulersProvider  провайдер потоков выполнения
     */
    public QuestionViewModel(IQuestionInteractor questionInteractor,
                             IStatisticInteractor statisticInteractor,
                             ISchedulersProvider schedulersProvider) {
        this.questionInteractor = questionInteractor;
        this.statisticInteractor = statisticInteractor;
        this.schedulersProvider = schedulersProvider;
    }

    /**
     * Получение вопроса с вариантами ответов
     *
     * @param typeQuestions тип вопроса
     * @param time          задержка перед получением вопроса
     */
    public void getQuestion(String typeQuestions,long time) {
        addDisposable(questionInteractor.getQuestion(typeQuestions)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .delay(time, TimeUnit.MILLISECONDS)
                .subscribe(questionLiveData::postValue));
    }

    /**
     * Отправка результата ответа
     *
     * @param questionId      идентификатор вопроса
     * @param answerIndex     индекс выбранного ответа
     * @param isCorrectAnswer признак правильности ответа
     * @param dateOfAnswer    дата ответа
     */
    public void addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        addDisposable(statisticInteractor.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribe(() -> statisticLiveData.setValue(true), t -> statisticLiveData.setValue(false)));
    }

    public LiveData<Question> getQuestionLiveData() {
        return questionLiveData;
    }

    public LiveData<Boolean> getStatisticLiveData() {
        return statisticLiveData;
    }
}
