package com.aka_npou.sberandroidschool_finalproject.presentation.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
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
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.SchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.profile.ProfileFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.question.QuestionFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.StatisticFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ISchedulersProvider schedulersProvider;

    private IFragmentNavigation mFragmentNavigation;

    private IQuestionInteractor mQuestionInteractor;
    private IStatisticInteractor mStatisticInteractor;
    private IProfileInteractor mProfileInteractor;

    private MainActivityViewModel viewModel;

    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
            public void onCreate (SupportSQLiteDatabase db) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("text", "a1");
                contentValues.put("question_id", "1");
                db.insert("answers", OnConflictStrategy.IGNORE, contentValues);

            }
            public void onOpen (@NonNull SupportSQLiteDatabase db) {

            }
        };*/

        AppDataBase appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "appDB.db")
                //.addCallback(rdc)
                .build();

        schedulersProvider = new SchedulersProvider();

        mFragmentNavigation = this::replaceFragment;

        IQuestionDao questionDao = appDataBase.getQuestionDao();
        IConverter mQuestionConverter = new QuestionWithAnswersConverter();
        IQuestionRepository questionRepository = new QuestionRepository(questionDao, mQuestionConverter);
        mQuestionInteractor = new QuestionInteractor(questionRepository);

        IStatisticDao statisticDao = appDataBase.getStatisticDao();
        StatisticConverter statisticConverter = new StatisticConverter();
        IStatisticRepository statisticRepository = new StatisticRepository(statisticDao, statisticConverter);
        mStatisticInteractor = new StatisticInteractor(statisticRepository);

//        IProfileDao profileDao = appDataBase.getProfileDao();
//        ProfileConverter profileConverter = new ProfileConverter();
//        IProfileRepository profileRepository = new ProfileRepository(profileDao, profileConverter);
        SharedPreferences sharedPreferences = getSharedPreferences("PROFILE", MODE_PRIVATE);
        IProfileStore profileStore = new ProfileStore(sharedPreferences);
        mProfileInteractor = new ProfileInteractor(profileStore);

        progressBar = findViewById(R.id.progress_main_activity);

        createViewModel();
        observeLiveData();

        viewModel.initDB();
    }

    private void replaceFragment(String tagFragment, boolean addToBackStack) {
        Fragment fragment = getFragment(tagFragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tagFragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    private Fragment getFragment(String tagFragment) {
        switch (tagFragment) {
            case "QuestionFragment": {
                return QuestionFragment.newInstance(mFragmentNavigation,
                        schedulersProvider,
                        mQuestionInteractor,
                        mStatisticInteractor);
            }
            case "SelectTypeGameFragment": {
                return SelectTypeGameFragment.newInstance(mFragmentNavigation);
            }
            case "ProfileFragment": {
                return ProfileFragment.newInstance(mFragmentNavigation,
                        schedulersProvider,
                        mProfileInteractor,
                        mStatisticInteractor);
            }
            case "StatisticFragment": {
                return StatisticFragment.newInstance(mFragmentNavigation,
                        schedulersProvider,
                        mStatisticInteractor);
            }
            default: {
                throw new IllegalArgumentException("not support fragment " + tagFragment);
            }
        }
    }

    private void showStartFragment() {
        replaceFragment(SelectTypeGameFragment.TAG, false);
    }

    private void createViewModel() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new MainActivityViewModel(mQuestionInteractor, schedulersProvider);
            }
        }).get(MainActivityViewModel.class);
    }

    private void observeLiveData() {
        viewModel.getProgressLiveData().observe(this, this::showProgress);
        viewModel.getInitDBLiveData().observe(this, this::showApp);
        viewModel.getErrorLiveData().observe(this, this::showError);
    }

    private void showProgress(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void showApp(boolean result) {
        showStartFragment();
    }

    private void showError(@NonNull Throwable throwable) {
        Log.e(TAG, "showError: ", throwable);
        Snackbar.make(findViewById(R.id.container), throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show();
    }
}