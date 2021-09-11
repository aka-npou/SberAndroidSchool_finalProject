package com.aka_npou.sberandroidschool_finalproject.data.dataBase;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.aka_npou.sberandroidschool_finalproject.data.entity.AnswerEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.QuestionTypeEntity;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;

@Database(entities = {QuestionEntity.class, StatisticEntity.class, AnswerEntity.class, QuestionTypeEntity.class},
        version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE question_types (type TEXT DEFAULT 'null', id INTEGER PRIMARY KEY autoincrement NOT NULL)");
            database.execSQL("ALTER TABLE questions ADD COLUMN question_type INTEGER DEFAULT 0 NOT NULL");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            ContentValues values = new ContentValues();
            values.put("id", "1");
            values.put("type", "арифметика");
            database.insert("question_types", SQLiteDatabase.CONFLICT_REPLACE, values);

            database.execSQL("UPDATE questions SET question_type = 1");
        }
    };

    public abstract IQuestionDao getQuestionDao();
    public abstract IStatisticDao getStatisticDao();
}
