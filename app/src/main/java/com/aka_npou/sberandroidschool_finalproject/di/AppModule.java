package com.aka_npou.sberandroidschool_finalproject.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.aka_npou.sberandroidschool_finalproject.data.converter.QuestionWithAnswersConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.StatisticConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.AppDataBase;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.repository.QuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.data.repository.StatisticRepository;
import com.aka_npou.sberandroidschool_finalproject.data.store.IProfileStore;
import com.aka_npou.sberandroidschool_finalproject.data.store.ProfileStore;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.ProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.QuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.StatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public interface AppModule {

    @Singleton
    @Provides
    static IQuestionInteractor provideIQuestionInteractor(AppDataBase appDataBase) {

        IQuestionDao questionDao = appDataBase.getQuestionDao();
        QuestionWithAnswersConverter mQuestionConverter = new QuestionWithAnswersConverter();
        IQuestionRepository questionRepository = new QuestionRepository(questionDao, mQuestionConverter);

        return new QuestionInteractor(questionRepository);
    }

    @Singleton
    @Provides
    static IStatisticInteractor provideIStatisticInteractor(AppDataBase appDataBase) {
        IStatisticDao statisticDao = appDataBase.getStatisticDao();
        StatisticConverter statisticConverter = new StatisticConverter();
        IStatisticRepository statisticRepository = new StatisticRepository(statisticDao, statisticConverter);
        return new StatisticInteractor(statisticRepository);
    }

    @Singleton
    @Provides
    static IProfileInteractor provideIProfileInteractor(SharedPreferences sharedPreferences) {
        IProfileStore profileStore = new ProfileStore(sharedPreferences);
        return new ProfileInteractor(profileStore);
    }

    @Singleton
    @Provides
    static AppDataBase getAppDataBase(Context context) {
        /*RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
            public void onCreate (SupportSQLiteDatabase db) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("text", "a1");
                contentValues.put("question_id", "1");
                db.insert("answers", OnConflictStrategy.IGNORE, contentValues);

            }
            public void onOpen (@NonNull SupportSQLiteDatabase db) {

            }
        };*/

        return Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "appDB.db")
                //.addCallback(rdc)
                .build();
    }

    @Provides
    @Singleton
    static SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences("PROFILE", Context.MODE_PRIVATE);
    }
}
