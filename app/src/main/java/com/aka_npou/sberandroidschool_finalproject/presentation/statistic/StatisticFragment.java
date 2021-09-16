package com.aka_npou.sberandroidschool_finalproject.presentation.statistic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;
import com.aka_npou.sberandroidschool_finalproject.domain.model.TotalStatistic;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.Utils;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.adapter.DetailedStatisticPerDayRecyclerAdapter;
import com.aka_npou.sberandroidschool_finalproject.presentation.statistic.adapter.StatisticRecyclerAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Фрагмент отображающий статистику ответов
 *
 * @author Мулярчук Александр
 */
public class StatisticFragment extends Fragment {
    public final static String TAG = StatisticFragment.class.getSimpleName();

    private OnClickDayStatisticHandler onClickDayStatisticHandler = new OnClickDayStatisticHandler() {
        @Override
        public void viewDayStatistic(Date date) {
            showDayStatistic(date);
        }
    };

    private RecyclerView statisticRecyclerView;
    private RecyclerView detailedStatisticRecyclerView;
    private View rootView;
    private View progressBar;

    private TextView countQuestions;
    private TextView percentCorrectAnswers;
    private TextView daysCount;

    private StatisticViewModel viewModel;

    /**
     * Получение фрагмента статистики
     *
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rootView = view.getRootView();
        statisticRecyclerView = view.findViewById(R.id.statistic_recycler_view);
        statisticRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL, false));

        detailedStatisticRecyclerView = view.findViewById(R.id.detailed_statistic_per_day);
        detailedStatisticRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false));

        progressBar = view.findViewById(R.id.progress_statistic);

        countQuestions = view.findViewById(R.id.count_questions);
        countQuestions.setText("n/a questions");
        percentCorrectAnswers = view.findViewById(R.id.percent_correct_answers);
        percentCorrectAnswers.setText("n/a %");
        daysCount = view.findViewById(R.id.days_count);
        daysCount.setText("n/a days");

        createViewModel();
        observeLiveData();

        if (savedInstanceState == null) {
            viewModel.getTotalStatistic();
        }

    }

    private void createViewModel() {
        //null может быть если делать до onAttached, а мы делаем в onViewCreated, что после
        ActivityComponent activityComponent = QuizApplication.getAppComponent(getActivity()).getActivityComponent();
        viewModel = new ViewModelProvider(this, activityComponent.getViewModelFactory())
                .get(StatisticViewModel.class);
    }

    private void observeLiveData() {
        viewModel.getProgressLiveData().observe(getViewLifecycleOwner(), this::showProgress);
        viewModel.getStatisticLiveData().observe(getViewLifecycleOwner(), this::showData);
        viewModel.getErrorLiveData().observe(getViewLifecycleOwner(), this::showError);
        viewModel.getTotalStatisticLiveData().observe(getViewLifecycleOwner(), this::showTotalData);
        viewModel.getExpListLiveData().observe(getViewLifecycleOwner(), this::showDayStatistic);
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
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -13);
        statisticPeriod.add(0, calendar.getTime());

        Log.i(TAG, "getStatisticPeriod: " + statisticPeriod.get(0) + " " + statisticPeriod.get(1));

        return statisticPeriod;
    }

    private void showProgress(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void showTotalData(TotalStatistic totalStatistic) {
        countQuestions.setText(String.format("%d %s",
                totalStatistic.getCountQuestions(),
                Utils.getQuestionAddition(totalStatistic.getCountQuestions())));
        if (totalStatistic.getCountQuestions() == 0) {
            percentCorrectAnswers.setText("n/a %");
        } else {
            percentCorrectAnswers.setText(String.format("%4.2f %%",
                    (100f * totalStatistic.getCountCorrectAnswers()) / totalStatistic.getCountQuestions()));
        }
        daysCount.setText(String.format("%d %s",
                totalStatistic.getDaysCount(),
                Utils.getDayAddition(totalStatistic.getDaysCount())));

        List<Date> statisticPeriod = getStatisticPeriod();
        viewModel.getStatisticForPeriod(statisticPeriod.get(0), statisticPeriod.get(1));
    }

    private void showData(@NonNull List<DailyStatistics> statisticList) {
        Log.i(TAG, "showData: " + statisticList.size());
        StatisticRecyclerAdapter adapter =
                new StatisticRecyclerAdapter(statisticList, onClickDayStatisticHandler);

        statisticRecyclerView.setAdapter(adapter);
        statisticRecyclerView.scrollToPosition(statisticList.size() - 1);
    }

    private void showError(@NonNull Throwable throwable) {
        Log.e(TAG, "showError: ", throwable);
        Snackbar.make(rootView, throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show();
    }

    private void showDayStatistic(Date date) {
        viewModel.getExplicitStatisticForPeriod(date);
    }

    private void showDayStatistic(List<DetailedStatisticPerPeriod> list) {
        Collections.sort(list, (o1, o2) -> {
            int p1 = 0;
            int p2 = 0;
            if (o1.getCountQuestions() != 0) {
                p1 = (int) (100f * o1.getCountCorrectQuestions() / o1.getCountQuestions());
            }
            if (o2.getCountQuestions() != 0) {
                p2 = (int) (100f * o2.getCountCorrectQuestions() / o2.getCountQuestions());
            }
            return p2 - p1;
        });

        DetailedStatisticPerDayRecyclerAdapter adapter =
                new DetailedStatisticPerDayRecyclerAdapter(list);

        detailedStatisticRecyclerView.setAdapter(adapter);
    }
}
