package com.aka_npou.sberandroidschool_finalproject.presentation.statistic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DailyStatistics;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Адаптер для отображения статистики ответов
 *
 * @author Мулярчук Александр
 */
public class StatisticRecyclerAdapter extends RecyclerView.Adapter<StatisticRecyclerAdapter.StatisticViewHolder> {

    private static final String pattern = "dd";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    private final List<DailyStatistics> mStatisticList;
    private int mMaxCountQuestionPerDay = 0;

    /**
     * Конструктор
     * @param statisticList {@link List} список статистики ответов по дням
     */
    public StatisticRecyclerAdapter(List<DailyStatistics> statisticList) {
        this.mStatisticList = statisticList;
        getMaxCountQuestionPerDay();
    }

    private void getMaxCountQuestionPerDay() {
        mMaxCountQuestionPerDay = 0;
        for (DailyStatistics dailyStatistics : mStatisticList) {
            mMaxCountQuestionPerDay = Math.max(mMaxCountQuestionPerDay, dailyStatistics.getCountQuestions());
        }
    }

    @NonNull
    @Override
    public StatisticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StatisticViewHolder vh = new StatisticViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_statistic, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticViewHolder holder, int position) {
        holder.bindView(mStatisticList.get(position), mMaxCountQuestionPerDay);
    }

    @Override
    public int getItemCount() {
        return mStatisticList.size();
    }

    /**
     * Holder для отображения статистики по дню
     */
    static class StatisticViewHolder extends RecyclerView.ViewHolder {

        ImageView statisticCountQuestions;
        ImageView statisticPercentageOfCorrectAnswers;
        TextView statisticDate;

        /**
         * Конструктор
         * @param itemView view отображаемого дня
         */
        public StatisticViewHolder(@NonNull View itemView) {
            super(itemView);

            statisticCountQuestions = itemView.findViewById(R.id.statistic_count_questions);
            statisticPercentageOfCorrectAnswers = itemView.findViewById(R.id.statistic_percentage_of_correct_answers);
            statisticDate = itemView.findViewById(R.id.statistic_date);
        }

        void bindView(@NonNull DailyStatistics dailyStatistics, int maxCountQuestionPerDay) {
            if (dailyStatistics.getCountQuestions() == 0) {
                statisticCountQuestions.getLayoutParams().height = 0;
                statisticPercentageOfCorrectAnswers.getLayoutParams().height = 0;
            } else {
                statisticCountQuestions.getLayoutParams().height = (int) (100 * dailyStatistics.getCountQuestions() / maxCountQuestionPerDay * itemView.getContext().getResources().getDisplayMetrics().density);
                statisticPercentageOfCorrectAnswers.getLayoutParams().height = (int) (100 * dailyStatistics.getCountCorrectQuestions() / dailyStatistics.getCountQuestions() * itemView.getContext().getResources().getDisplayMetrics().density);
            }
            statisticDate.setText(simpleDateFormat.format(dailyStatistics.getDateOfAnswer()));
        }
    }
}
