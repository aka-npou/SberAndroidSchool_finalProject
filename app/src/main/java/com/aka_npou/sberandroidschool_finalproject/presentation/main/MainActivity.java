package com.aka_npou.sberandroidschool_finalproject.presentation.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.data.converter.ProfileConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.QuestionConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.StatisticConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IProfileDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase.InClassDataBase;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase.InClassProfileDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase.InClassQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataBase.inClassDataBase.InClassStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.repository.ProfileRepository;
import com.aka_npou.sberandroidschool_finalproject.data.repository.QuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.data.repository.StatisticRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.ProfileInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.QuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.StatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IProfileRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.SchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.profile.ProfileFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.question.QuestionFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.StatisticFragment;

public class MainActivity extends AppCompatActivity {

    private ISchedulersProvider schedulersProvider;

    private IFragmentNavigation mFragmentNavigation;

    private IQuestionInteractor mQuestionInteractor;
    private IStatisticInteractor mStatisticInteractor;
    private IProfileInteractor mProfileInteractor;
    private QuestionConverter mQuestionConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedulersProvider = new SchedulersProvider();

        mFragmentNavigation = this::replaceFragment;

        InClassDataBase dataBase = new InClassDataBase();

        IQuestionDao questionDao = new InClassQuestionDao(dataBase);
        mQuestionConverter = new QuestionConverter();
        IQuestionRepository questionRepository = new QuestionRepository(questionDao, mQuestionConverter);
        mQuestionInteractor = new QuestionInteractor(questionRepository);

        IStatisticDao statisticDao = new InClassStatisticDao(dataBase);
        StatisticConverter statisticConverter = new StatisticConverter();
        IStatisticRepository statisticRepository = new StatisticRepository(statisticDao, statisticConverter);
        mStatisticInteractor = new StatisticInteractor(statisticRepository);

        IProfileDao profileDao = new InClassProfileDao(dataBase);
        ProfileConverter profileConverter = new ProfileConverter();
        IProfileRepository profileRepository = new ProfileRepository(profileDao, profileConverter);
        mProfileInteractor = new ProfileInteractor(profileRepository);

        showStartFragment();
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
}