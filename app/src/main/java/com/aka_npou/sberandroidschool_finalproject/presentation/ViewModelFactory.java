package com.aka_npou.sberandroidschool_finalproject.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.main.MainActivityViewModel;
import com.aka_npou.sberandroidschool_finalproject.presentation.profile.ProfileViewModel;
import com.aka_npou.sberandroidschool_finalproject.presentation.question.QuestionViewModel;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeQuestions.SelectTypeQuestionsFragmentViewModel;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.StatisticViewModel;

/**
 * Фабрика создания ViewModel для фрагментов
 *
 * @author Мулярчук Александр
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    private final IQuestionInteractor questionInteractor;
    private final IStatisticInteractor statisticInteractor;
    private final IProfileInteractor profileInteractor;
    private final ISchedulersProvider schedulersProvider;

    /**
     * Конструктор
     *
     * @param questionInteractor  интерактор для работы с данными по вопросам и ответам
     * @param statisticInteractor интерактор для работы с данными по статистике
     * @param profileInteractor   интерактор для работы с данными профиля
     * @param schedulersProvider  провайдер потоков выполнения
     */
    public ViewModelFactory(IQuestionInteractor questionInteractor,
                            IStatisticInteractor statisticInteractor,
                            IProfileInteractor profileInteractor,
                            ISchedulersProvider schedulersProvider) {
        this.questionInteractor = questionInteractor;
        this.statisticInteractor = statisticInteractor;
        this.profileInteractor = profileInteractor;
        this.schedulersProvider = schedulersProvider;
    }

    /**
     * Получает {@link ViewModel} по переданному классу
     *
     * @param modelClass класс который нужно получить
     * @param <T>        тип расширенный от {@link ViewModel} который нужно получить
     * @return класс расширенный от {@link ViewModel}
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (MainActivityViewModel.class.equals(modelClass)) {
            return (T) new MainActivityViewModel(questionInteractor, schedulersProvider);
        }
        if (ProfileViewModel.class.equals(modelClass)) {
            return (T) new ProfileViewModel(profileInteractor, schedulersProvider);
        }
        if (QuestionViewModel.class.equals(modelClass)) {
            return (T) new QuestionViewModel(questionInteractor, statisticInteractor, schedulersProvider);
        }
        if (StatisticViewModel.class.equals(modelClass)) {
            return (T) new StatisticViewModel(statisticInteractor, schedulersProvider);
        }
        if (SelectTypeQuestionsFragmentViewModel.class.equals(modelClass)) {
            return (T) new SelectTypeQuestionsFragmentViewModel(questionInteractor, schedulersProvider);
        }

        throw new ClassCastException();
    }
}
