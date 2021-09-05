package com.aka_npou.sberandroidschool_finalproject.presentation.profile;

import android.os.Bundle;
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
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.StatisticFragment;

public class ProfileFragment extends Fragment {
    public final static String TAG = ProfileFragment.class.getSimpleName();

    private final IFragmentNavigation mFragmentNavigation;
    private final ISchedulersProvider mSchedulersProvider;
    private final IStatisticInteractor mStatisticInteractor;

    private ProfileViewModel mViewModel;


    public static Fragment newInstance(IFragmentNavigation fragmentNavigation,
                                       ISchedulersProvider schedulersProvider,
                                       IStatisticInteractor statisticInteractor) {
        return new ProfileFragment(fragmentNavigation, schedulersProvider, statisticInteractor);
    }

    public ProfileFragment(IFragmentNavigation fragmentNavigation,
                             ISchedulersProvider schedulersProvider,
                             IStatisticInteractor statisticInteractor) {
        mFragmentNavigation = fragmentNavigation;
        mSchedulersProvider = schedulersProvider;
        mStatisticInteractor = statisticInteractor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getChildFragmentManager().beginTransaction()
                .add(R.id.statistic_container,
                        StatisticFragment.newInstance(mFragmentNavigation,
                                mSchedulersProvider,
                                mStatisticInteractor),
                        SelectTypeGameFragment.TAG)
                .commit();

        /*mRootView = view.getRootView();
        mStatisticRecyclerView = view.findViewById(R.id.statistic_recycler_view);
        mStatisticRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        mProgressBar = view.findViewById(R.id.progress_frame_layout);

        createViewModel();
        observeLiveData();

        if (savedInstanceState == null) {
            List<Date> statisticPeriod = getStatisticPeriod();
            mViewModel.getStatisticAsyncRx(statisticPeriod.get(0), statisticPeriod.get(1));
        }*/

    }
}
