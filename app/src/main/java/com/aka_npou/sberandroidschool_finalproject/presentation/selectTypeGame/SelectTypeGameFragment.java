package com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.data.converter.QuestionConverter;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.question.QuestionFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.StatisticFragment;

public class SelectTypeGameFragment extends Fragment {
    public final static String TAG = SelectTypeGameFragment.class.getSimpleName();

    private final IFragmentNavigation mFragmentNavigation;
    private final ISchedulersProvider mSchedulersProvider;

    private final IQuestionInteractor mQuestionInteractor;
    private final IStatisticInteractor mStatisticInteractor;
    private final QuestionConverter mQuestionConverter;

    public static Fragment newInstance(IFragmentNavigation fragmentNavigation,
                                       ISchedulersProvider schedulersProvider,
                                       IQuestionInteractor questionInteractor,
                                       IStatisticInteractor statisticInteractor,
                                       QuestionConverter questionConverter) {
        return new SelectTypeGameFragment(fragmentNavigation, schedulersProvider, questionInteractor, statisticInteractor, questionConverter);
    }

    public SelectTypeGameFragment(IFragmentNavigation fragmentNavigation,
                                  ISchedulersProvider schedulersProvider,
                                  IQuestionInteractor questionInteractor,
                                  IStatisticInteractor statisticInteractor,
                                  QuestionConverter questionConverter) {
        mFragmentNavigation = fragmentNavigation;
        mSchedulersProvider = schedulersProvider;
        mQuestionInteractor = questionInteractor;
        mStatisticInteractor = statisticInteractor;
        mQuestionConverter = questionConverter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_type_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button buttonStartSimpleGame = view.findViewById(R.id.start_simple_game_button);
        buttonStartSimpleGame.setOnClickListener(viewButton ->
                mFragmentNavigation.replace(
                        QuestionFragment.newInstance(mFragmentNavigation,
                                mSchedulersProvider,
                                mQuestionInteractor,
                                mStatisticInteractor,
                                mQuestionConverter),
                        QuestionFragment.TAG,
                        false));

        Button buttonOpenProfile = view.findViewById(R.id.open_profile_button);
        buttonOpenProfile.setOnClickListener(viewButton ->
                mFragmentNavigation.replace(
                        StatisticFragment.newInstance(mFragmentNavigation,
                                mSchedulersProvider,
                                mStatisticInteractor),
                        StatisticFragment.TAG,
                        true));
    }
}
