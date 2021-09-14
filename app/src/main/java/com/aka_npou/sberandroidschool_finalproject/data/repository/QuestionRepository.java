package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.QuestionWithAnswersAndTypeConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionTypeEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswersAndType;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionWithAnswersAndType;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Имплементация интерфейса {@link IQuestionRepository} репозитория для получения информации
 *  о вопросах и вариантах ответов из базы данных
 *
 *  @author Мулярчук Александр
 */
public class QuestionRepository implements IQuestionRepository {
    private final IQuestionDao questionDao;
    private final IConverter<Question, QuestionWithAnswersAndType> converter;

    /**
     * Конструктор
     *
     * @param questionDao Dao для работы с базой данных
     * @param converter конвертер модели дата слоя в модель домейн слоя и обратно
     */
    public QuestionRepository(IQuestionDao questionDao, IConverter<Question, QuestionWithAnswersAndType> converter) {
        this.questionDao = questionDao;
        this.converter = converter;
    }

    @Override
    public Question getQuestion(String typeQuestions) {
        return converter.reverse(questionDao.getUncommonQuestion(typeQuestions));
    }

    @Override
    public boolean initDB() {
        if (questionDao.getQuestion() != null) {
            return true;
        }

        questionDao.insert(converter.convert(new Question(1,
                "2+2",
                Arrays.asList("1", "2", "3", "4"),
                3,
                "арифметика")));
        questionDao.insert(converter.convert(new Question(2,
                "3+3",
                Arrays.asList("6", "2", "3", "4"),
                0,
                "арифметика")));
        questionDao.insert(converter.convert(new Question(3,
                "4+4",
                Arrays.asList("1", "8", "3", "4"),
                1,
                "арифметика")));
        questionDao.insert(converter.convert(new Question(4,
                "5+5",
                Arrays.asList("1", "2", "10", "4"),
                2,
                "арифметика")));
        questionDao.insert(converter.convert(new Question(5,
                "6+6",
                Arrays.asList("1", "12", "10", "4"),
                1,
                "арифметика")));
        questionDao.insert(converter.convert(new Question(6,
                "7+7",
                Arrays.asList("1", "2", "10", "14"),
                3,
                "арифметика")));
        questionDao.insert(converter.convert(new Question(7,
                "8+8",
                Arrays.asList("1", "2", "10", "16"),
                3,
                "арифметика")));
        questionDao.insert(converter.convert(new Question(8,
                "9+9",
                Arrays.asList("1", "18", "10", "4"),
                1,
                "арифметика")));

        questionDao.insert(converter.convert(new Question(9,
                "Столица России",
                Arrays.asList("Москва", "Питер", "Рязань", "Мурманск"),
                0,
                "география")));
        questionDao.insert(converter.convert(new Question(10,
                "Столица Польши",
                Arrays.asList("Гданьск", "Варшава", "Краков", "Лодзь"),
                1,
                "география")));
        questionDao.insert(converter.convert(new Question(11,
                "Столица Франции",
                Arrays.asList("Бордо", "Москва", "Лион", "Париж"),
                3,
                "география")));


        return true;
    }

    @Override
    public List<String> getQuestionTypes() {
        List<QuestionTypeEntity> questionTypeEntityList = questionDao.getQuestionTypes();
        List<String> questionTypeList = new ArrayList<>();

        for (QuestionTypeEntity entity:questionTypeEntityList) {
            questionTypeList.add(entity.type);
        }

        return questionTypeList;
    }
}
