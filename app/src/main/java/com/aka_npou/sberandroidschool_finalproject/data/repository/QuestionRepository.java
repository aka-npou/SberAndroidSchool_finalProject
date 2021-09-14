package com.aka_npou.sberandroidschool_finalproject.data.repository;

import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.QuestionWithAnswersAndTypeConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionTypeEntity;
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
    private final IQuestionDao mQuestionDao;
    private final IConverter<Question, QuestionWithAnswersAndType> mConverter;

    /**
     * Конструктор
     *
     * @param questionDao Dao для работы с базой данных
     * @param converter конвертер модели дата слоя в модель домейн слоя и обратно
     */
    public QuestionRepository(IQuestionDao questionDao, IConverter<Question, QuestionWithAnswersAndType> converter) {
        this.mQuestionDao = questionDao;
        mConverter = converter;
    }

    @Override
    public Question getQuestion(String typeQuestions) {
        return mConverter.reverse(mQuestionDao.getUncommonQuestion(typeQuestions));
    }

    @Override
    public boolean initDB() {
        if (mQuestionDao.getQuestion() != null) {
            return true;
        }

        mQuestionDao.insert(mConverter.convert(new Question(1, "2+2", Arrays.asList("1", "2", "3", "4"), 3, "арифметика")));
        mQuestionDao.insert(mConverter.convert(new Question(2, "3+3", Arrays.asList("6", "2", "3", "4"), 0, "арифметика")));
        mQuestionDao.insert(mConverter.convert(new Question(3, "4+4", Arrays.asList("1", "8", "3", "4"), 1, "арифметика")));
        mQuestionDao.insert(mConverter.convert(new Question(4, "5+5", Arrays.asList("1", "2", "10", "4"), 2, "арифметика")));
        mQuestionDao.insert(mConverter.convert(new Question(5, "6+6", Arrays.asList("1", "12", "10", "4"), 1, "арифметика")));
        mQuestionDao.insert(mConverter.convert(new Question(6, "7+7", Arrays.asList("1", "2", "10", "14"), 3, "арифметика")));
        mQuestionDao.insert(mConverter.convert(new Question(7, "8+8", Arrays.asList("1", "2", "10", "16"), 3, "арифметика")));
        mQuestionDao.insert(mConverter.convert(new Question(8, "9+9", Arrays.asList("1", "18", "10", "4"), 1, "арифметика")));

        mQuestionDao.insert(mConverter.convert(new Question(9, "Столица России", Arrays.asList("Москва", "Питер", "Рязань", "Мурманск"), 0, "география")));
        mQuestionDao.insert(mConverter.convert(new Question(10, "Столица Польши", Arrays.asList("Гданьск", "Варшава", "Краков", "Лодзь"), 1, "география")));
        mQuestionDao.insert(mConverter.convert(new Question(11, "Столица Франции", Arrays.asList("Бордо", "Москва", "Лион", "Париж"), 3, "география")));

        return true;
    }

    @Override
    public List<String> getQuestionTypes() {
        List<QuestionTypeEntity> questionTypeEntityList = mQuestionDao.getQuestionTypes();
        List<String> questionTypeList = new ArrayList<>();

        for (QuestionTypeEntity entity:questionTypeEntityList) {
            questionTypeList.add(entity.type);
        }

        return questionTypeList;
    }
}
