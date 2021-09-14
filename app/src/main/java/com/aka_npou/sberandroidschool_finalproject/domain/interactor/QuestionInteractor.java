package com.aka_npou.sberandroidschool_finalproject.domain.interactor;

import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *  Имплементация интерфейса {@link IQuestionInteractor}
 *
 *  @author Мулярчук Александр
 */
public class QuestionInteractor implements IQuestionInteractor{
    private final IQuestionRepository questionRepository;

    /**
     * Конструктор
     * @param questionRepository {@link IQuestionRepository} репозиторий для работы с вопросами и ответами
     */
    public QuestionInteractor(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Single<Question> getQuestion(String typeQuestions) {
        return Single.fromCallable(() -> questionRepository.getQuestion(typeQuestions));
    }

    @Override
    public Completable initDB() {
        return Completable.fromAction(questionRepository::initDB);
    }

    @Override
    public Single<List<String>> getQuestionTypes() {
        return Single.fromCallable(questionRepository::getQuestionTypes);
    }
}
