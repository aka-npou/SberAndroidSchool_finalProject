package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswers;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

import java.util.Arrays;

/**
 *  Имплементация интерфейса {@link IQuestionRepository} репозитория для получения информации
 *  о вопросах и вариантах ответов из базы данных
 *
 *  @author Мулярчук Александр
 */
public class QuestionRepository implements IQuestionRepository {
    private final IQuestionDao questionDao;
    private final IConverter<Question, QuestionWithAnswers> converter;

    /**
     * Конструктор
     *
     * @param questionDao Dao для работы с базой данных
     * @param converter конвертер модели дата слоя в модель домейн слоя и обратно
     */
    public QuestionRepository(IQuestionDao questionDao, IConverter<Question, QuestionWithAnswers> converter) {
        this.questionDao = questionDao;
        this.converter = converter;
    }

    @Override
    public Question getQuestion() {
        return converter.reverse(questionDao.getUncommonQuestion());
    }

    @Override
    public boolean initDB() {
        if (questionDao.getQuestion() != null) {
            return true;
        }

        questionDao.insert(converter.convert(new Question(1,
                "2+2",
                Arrays.asList("1", "2", "3", "4"),
                3)));
        questionDao.insert(converter.convert(new Question(2,
                "3+3",
                Arrays.asList("6", "2", "3", "4"),
                0)));
        questionDao.insert(converter.convert(new Question(3,
                "4+4",
                Arrays.asList("1", "8", "3", "4"),
                1)));
        questionDao.insert(converter.convert(new Question(4,
                "5+5",
                Arrays.asList("1", "2", "10", "4"),
                2)));
        questionDao.insert(converter.convert(new Question(5,
                "6+6",
                Arrays.asList("1", "12", "10", "4"),
                1)));
        questionDao.insert(converter.convert(new Question(6,
                "7+7",
                Arrays.asList("1", "2", "10", "14"),
                3)));
        questionDao.insert(converter.convert(new Question(7,
                "8+8",
                Arrays.asList("1", "2", "10", "16"),
                3)));
        questionDao.insert(converter.convert(new Question(8,
                "9+9",
                Arrays.asList("1", "18", "10", "4"),
                1)));

        return true;
    }
}
