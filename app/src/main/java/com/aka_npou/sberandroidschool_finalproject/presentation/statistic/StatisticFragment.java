package com.aka_npou.sberandroidschool_finalproject.presentation.statistic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;

public class StatisticFragment extends Fragment {
    public final static String TAG = StatisticFragment.class.getSimpleName();

    private final IFragmentNavigation mFragmentNavigation;
    private final ISchedulersProvider mSchedulersProvider;

    private final IStatisticInteractor mStatisticInteractor;

    public static Fragment newInstance(IFragmentNavigation fragmentNavigation,
                                       ISchedulersProvider schedulersProvider,
                                       IStatisticInteractor statisticInteractor) {
        return new StatisticFragment(fragmentNavigation, schedulersProvider, statisticInteractor);
    }

    public StatisticFragment(IFragmentNavigation fragmentNavigation,
                                  ISchedulersProvider schedulersProvider,
                                  IStatisticInteractor statisticInteractor) {
        mFragmentNavigation = fragmentNavigation;
        mSchedulersProvider = schedulersProvider;
        mStatisticInteractor = statisticInteractor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
