package com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeQuestions;

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
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.OnClickTypeQuestionsHandler;
import com.aka_npou.sberandroidschool_finalproject.presentation.question.QuestionFragment;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeQuestions.adapter.SelectTypeQuestionsRecyclerAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * Фрагмент отображающий выбор типа вопросов
 *
 * @author Мулярчук Александр
 */
public class SelectTypeQuestionsFragment extends Fragment {
    public final static String TAG = SelectTypeQuestionsFragment.class.getSimpleName();

    private final OnClickTypeQuestionsHandler onClickTypeQuestionsHandler = new OnClickTypeQuestionsHandler() {
        @Override
        public void startGame(String typeQuestions) {
            setTypeQuestionsHandler.startGame(typeQuestions);
            fragmentNavigation.replace(QuestionFragment.TAG, true);
        }
    };

    private final OnClickTypeQuestionsHandler setTypeQuestionsHandler;

    private SelectTypeQuestionsFragmentViewModel viewModel;

    private final IFragmentNavigation fragmentNavigation;

    private RecyclerView questionTypesRecyclerView;
    private View rootView;
    private View progressBar;

    /**
     * Получение фрагмента выбора типа вопросов
     *
     * @param fragmentNavigation      обработчик для перехода между фрагментами
     * @param setTypeQuestionsHandler обработчик установки типа вопросов
     * @return {@link SelectTypeQuestionsFragment} фрагмент отображающий выбор типа вопросов
     */
    public static Fragment newInstance(IFragmentNavigation fragmentNavigation, OnClickTypeQuestionsHandler setTypeQuestionsHandler) {
        return new SelectTypeQuestionsFragment(fragmentNavigation, setTypeQuestionsHandler);
    }

    /**
     * Конструктор
     *
     * @param fragmentNavigation обработчик для перехода между фрагментами
     */
    public SelectTypeQuestionsFragment(IFragmentNavigation fragmentNavigation,
                                       OnClickTypeQuestionsHandler setTypeQuestionsHandler) {
        this.fragmentNavigation = fragmentNavigation;
        this.setTypeQuestionsHandler = setTypeQuestionsHandler;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_type_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rootView = view.getRootView();
        questionTypesRecyclerView = view.findViewById(R.id.question_types_recycler_view);
        questionTypesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        progressBar = view.findViewById(R.id.progress_question_types);

        createViewModel();
        observeLiveData();

        viewModel.getQuestionTypes();
    }

    private void createViewModel() {
        //null может быть если делать до onAttached, а мы делаем в onViewCreated, что после
        ActivityComponent activityComponent = QuizApplication.getAppComponent(getActivity()).getActivityComponent();
        viewModel = new ViewModelProvider(this, activityComponent.getViewModelFactory()).get(SelectTypeQuestionsFragmentViewModel.class);
    }

    private void observeLiveData() {
        viewModel.getProgressLiveData().observe(getViewLifecycleOwner(), this::showProgress);
        viewModel.getQuestionTypeData().observe(getViewLifecycleOwner(), this::showData);
        viewModel.getErrorLiveData().observe(getViewLifecycleOwner(), this::showError);
    }

    private void showProgress(boolean isVisible) {
        Log.i(TAG, "showProgress: " + isVisible);
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void showData(@NonNull List<String> questionTypesList) {
        Log.i(TAG, "showData: " + questionTypesList.size());
        SelectTypeQuestionsRecyclerAdapter adapter =
                new SelectTypeQuestionsRecyclerAdapter(questionTypesList, onClickTypeQuestionsHandler);

        questionTypesRecyclerView.setAdapter(adapter);
    }

    private void showError(@NonNull Throwable throwable) {
        Log.e(TAG, "showError: ", throwable);
        Snackbar.make(rootView, throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show();
    }
}
