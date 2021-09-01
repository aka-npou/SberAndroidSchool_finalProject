package com.aka_npou.sberandroidschool_finalproject.presentation.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.data.converter.IConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.QuestionConverter;
import com.aka_npou.sberandroidschool_finalproject.data.converter.StatisticConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase.InClassDataBase;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase.InClassQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase.InClassStatisticDao;
import com.aka_npou.sberandroidschool_finalproject.data.entity.StatisticEntity;
import com.aka_npou.sberandroidschool_finalproject.data.repository.QuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.data.repository.StatisticRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.QuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.StatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Statistic;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IStatisticRepository;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.SchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;

public class MainActivity extends AppCompatActivity {

    private ISchedulersProvider schedulersProvider;

    private IFragmentNavigation mFragmentNavigation;

    private IQuestionInteractor mQuestionInteractor;
    private IStatisticInteractor mStatisticInteractor;
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

        showStartFragment();
    }

    private void replaceFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    private void showStartFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,
                        SelectTypeGameFragment.newInstance(mFragmentNavigation,
                                schedulersProvider,
                                mQuestionInteractor,
                                mStatisticInteractor,
                                mQuestionConverter),
                        SelectTypeGameFragment.TAG)
                .commit();
    }
}