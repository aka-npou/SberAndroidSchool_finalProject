package com.aka_npou.sberandroidschool_finalproject.presentation.statistic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.domain.model.DetailedStatisticPerPeriod;

import java.util.List;

/**
 * Адаптер для отображения детальной статистики ответов за день
 *
 * @author Мулярчук Александр
 */
public class DetailedStatisticPerDayRecyclerAdapter extends RecyclerView.Adapter<DetailedStatisticPerDayRecyclerAdapter.ViewHolder> {

    private final List<DetailedStatisticPerPeriod> detailedStatisticPerPeriodList;

    /**
     * Конструктор
     *
     * @param detailedStatisticPerPeriodList Список {@link List} из {@link DetailedStatisticPerPeriod}
     *                                       статистики ответов за день
     */
    public DetailedStatisticPerDayRecyclerAdapter(List<DetailedStatisticPerPeriod> detailedStatisticPerPeriodList) {
        this.detailedStatisticPerPeriodList = detailedStatisticPerPeriodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_detailed_statistic_per_day, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(detailedStatisticPerPeriodList.get(position));
    }

    @Override
    public int getItemCount() {
        return detailedStatisticPerPeriodList.size();
    }

    /**
     * Holder для отображения детальной статистики по дню
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemDetailedStatisticPerDayType;
        private final TextView itemDetailedStatisticPerDayCountQuestions;
        private final TextView itemDetailedStatisticPerDayPercentCorrectQuestions;

        /**
         * Конструктор
         *
         * @param itemView view отображаемого дня
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemDetailedStatisticPerDayType =
                    itemView.findViewById(R.id.item_detailed_statistic_per_day_type);
            itemDetailedStatisticPerDayCountQuestions =
                    itemView.findViewById(R.id.item_detailed_statistic_per_day_count_questions);
            itemDetailedStatisticPerDayPercentCorrectQuestions =
                    itemView.findViewById(R.id.item_detailed_statistic_per_day_percent_correct_questions);
        }

        void bindView(@NonNull DetailedStatisticPerPeriod detailedStatisticPerPeriod) {
            itemDetailedStatisticPerDayType.setText(detailedStatisticPerPeriod.getType());
            itemDetailedStatisticPerDayCountQuestions.setText(
                    String.format("%d questions",
                            detailedStatisticPerPeriod.getCountQuestions()));
            if (detailedStatisticPerPeriod.getCountQuestions() == 0) {
                itemDetailedStatisticPerDayPercentCorrectQuestions.setText("n/a %");
            } else {
                itemDetailedStatisticPerDayPercentCorrectQuestions.setText(
                        String.format("%4.2f %%",
                                100f * detailedStatisticPerPeriod.getCountCorrectQuestions()
                                        / detailedStatisticPerPeriod.getCountQuestions()));
            }
        }
    }
}
