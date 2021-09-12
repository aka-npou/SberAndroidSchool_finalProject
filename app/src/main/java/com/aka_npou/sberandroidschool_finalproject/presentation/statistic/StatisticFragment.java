package com.aka_npou.sberandroidschool_finalproject.presentation.statistic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aka_npou.sberandroidschool_finalproject.QuizApplication;
import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.di.activity.ActivityComponent;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.adapter.StatisticRecyclerAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Фрагмент отображающий статистику ответов
 *
 * @author Мулярчук Александр
 */
public class StatisticFragment extends Fragment {
    public final static String TAG = StatisticFragment.class.getSimpleName();

    private RecyclerView mStatisticRecyclerView;
    private View mRootView;
    private View mProgressBar;

    private StatisticViewModel viewModel;


    /**
     * Получение фрагмента статистики
     * @return {@link StatisticFragment} фрагмент отображающий статистику
     */
    public static Fragment newInstance() {
        return new StatisticFragment();
    }

    /**
     * Конструктор
     */
    public StatisticFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRootView = view.getRootView();
        mStatisticRecyclerView = view.findViewById(R.id.statistic_recycler_view);
        mStatisticRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        mProgressBar = view.findViewById(R.id.progress_frame_layout);

        createViewModel();
        observeLiveData();

        if (savedInstanceState == null) {
            List<Date> statisticPeriod = getStatisticPeriod();
            viewModel.getStatisticAsyncRx(statisticPeriod.get(0), statisticPeriod.get(1));
        }

    }

    private List<Date> getStatisticPeriod() {
        List<Date> statisticPeriod = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                23,
                59,
                59);
        calendar.set(Calendar.MILLISECOND, 999);
        statisticPeriod.add(calendar.getTime());

        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                0,
                0,
                0);
        calendar.add(Calendar.DATE, -14);
        statisticPeriod.add(0, calendar.getTime());

        Log.i(TAG, "getStatisticPeriod: " + statisticPeriod.get(0) + " " + statisticPeriod.get(1));

        return statisticPeriod;
    }

    private void createViewModel() {
        //null может быть если делать до onAttached, а мы делаем в onViewCreated, что после
        ActivityComponent activityComponent = QuizApplication.getAppComponent(getActivity()).getActivityComponent();
        viewModel = new ViewModelProvider(this, activityComponent.getViewModelFactory()).get(StatisticViewModel.class);
    }

    private void observeLiveData() {
        viewModel.getProgressLiveData().observe(getViewLifecycleOwner(), this::showProgress);
        viewModel.getStatisticLiveData().observe(getViewLifecycleOwner(), this::showData);
        viewModel.getErrorLiveData().observe(getViewLifecycleOwner(), this::showError);
    }

    private void showProgress(boolean isVisible) {
        mProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void showData(@NonNull List<DailyStatistics> statisticList) {
        Log.i(TAG, "showData: " + statisticList.size());
        StatisticRecyclerAdapter adapter =
                new StatisticRecyclerAdapter(statisticList);

        mStatisticRecyclerView.setAdapter(adapter);
    }

    private void showError(@NonNull Throwable throwable) {
        Log.e(TAG, "showError: ", throwable);
        Snackbar.make(mRootView, throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show();
    }
}
