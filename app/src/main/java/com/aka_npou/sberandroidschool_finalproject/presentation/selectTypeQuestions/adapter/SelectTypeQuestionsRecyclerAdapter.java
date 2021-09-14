package com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeQuestions.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.OnClickTypeQuestionsHandler;

import java.util.List;

/**
 * Адаптер для отображения типов вопросов
 *
 * @author Мулярчук Александр
 */
public class SelectTypeQuestionsRecyclerAdapter extends RecyclerView.Adapter<SelectTypeQuestionsRecyclerAdapter.SelectTypeQuestionsViewHolder> {

    private final List<String> questionTypes;
    private final OnClickTypeQuestionsHandler onClickTypeQuestionsHandler;

    /**
     * Конструктор
     *
     * @param questionTypes Список {@link List} из {@link String} типов вопросов
     * @param onClickTypeQuestionsHandler {@link OnClickTypeQuestionsHandler} хэндлер нажатия на тип вопросов для старта игры
     */
    public SelectTypeQuestionsRecyclerAdapter(List<String> questionTypes, OnClickTypeQuestionsHandler onClickTypeQuestionsHandler) {
        this.questionTypes = questionTypes;
        this.onClickTypeQuestionsHandler = onClickTypeQuestionsHandler;
    }

    @NonNull
    @Override
    public SelectTypeQuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SelectTypeQuestionsViewHolder vh = new SelectTypeQuestionsViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_type_questions, parent, false));

        vh.button.setOnClickListener(v -> onClickTypeQuestionsHandler.startGame(questionTypes.get(vh.getAdapterPosition())));

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectTypeQuestionsViewHolder holder, int position) {
        holder.bindView(questionTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return questionTypes.size();
    }

    /**
     * Holder для отображения типа вопроса
     */
    static class SelectTypeQuestionsViewHolder extends RecyclerView.ViewHolder {

        Button button;

        /**
         * Конструктор
         *
         * @param itemView view отображаемого типа вопроса
         */
        public SelectTypeQuestionsViewHolder(@NonNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.item_type_questions_button);
        }

        void bindView(@NonNull String questionType) {
            button.setText(questionType);
        }
    }
}
