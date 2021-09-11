package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *  Имплементация интерфейса {@link IQuestionInteractor}
 *
 *  @author Мулярчук Александр
 */
public class QuestionInteractor implements IQuestionInteractor{
    private final IQuestionRepository mQuestionRepository;

    /**
     * Конструктор
     * @param questionRepository {@link IQuestionRepository} репозиторий для работы с вопросами и ответами
     */
    public QuestionInteractor(IQuestionRepository questionRepository) {
        this.mQuestionRepository = questionRepository;
    }

    @Override
    public Single<Question> getQuestion() {
        return Single.fromCallable(mQuestionRepository::getQuestion);
    }

    @Override
    public Completable initDB() {
        return Completable.fromAction(mQuestionRepository::initDB);
    }
}
