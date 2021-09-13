package com.aka_npou.sberandroidschool_finalproject.presentation.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;

/**
 * ViewModel для отображения вопроса
 *
 * @author Мулярчук Александр
 */
public class QuestionViewModel extends ViewModel {
    private final IQuestionInteractor mQuestionInteractor;
    private final IStatisticInteractor mStatisticInteractor;
    private final ISchedulersProvider mSchedulersProvider;

    private final MutableLiveData<Question> mQuestionLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mStatisticLiveData = new MutableLiveData<>();

    private Disposable mDisposable;

    /**
     * Конструктор
     * @param questionInteractor интерактор для получения вопросов и ответов
     * @param statisticInteractor интерактор для отправки результата ответа
     * @param schedulersProvider провайдер потоков выполнения
     */
    public QuestionViewModel(IQuestionInteractor questionInteractor,
                             IStatisticInteractor statisticInteractor,
                             ISchedulersProvider schedulersProvider) {
        mQuestionInteractor = questionInteractor;
        mStatisticInteractor = statisticInteractor;
        mSchedulersProvider = schedulersProvider;
    }

    /**
     * Получение вопроса с вариантами ответов
     * @param time задержка перед получением вопроса
     */
    public void getQuestion(long time) {
        mDisposable = mQuestionInteractor.getQuestion()
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .delay(time, TimeUnit.MILLISECONDS)
                .subscribe(mQuestionLiveData::postValue);
    }

    /**
     * Отправка результата ответа
     * @param questionId идентификатор вопроса
     * @param answerIndex индекс выбранного ответа
     * @param isCorrectAnswer признак правильности ответа
     * @param dateOfAnswer дата ответа
     */
    public void addAnswerResult(long questionId, int answerIndex, boolean isCorrectAnswer, Date dateOfAnswer) {
        mDisposable = mStatisticInteractor.addAnswerResult(questionId, answerIndex, isCorrectAnswer, dateOfAnswer)
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .subscribe(() -> mStatisticLiveData.setValue(true), t -> mStatisticLiveData.setValue(false));
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    public LiveData<Question> getQuestionLiveData() {
        return mQuestionLiveData;
    }

    public LiveData<Boolean> getStatisticLiveData() {
        return mStatisticLiveData;
    }
}
