package com.aka_npou.sberandroidschool_finalproject.presentation.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IDataBaseApi;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase.InClassDataBase;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase.InClassDataBaseApi;
import com.aka_npou.sberandroidschool_finalproject.data.model.Question;
import com.aka_npou.sberandroidschool_finalproject.data.repository.InClassQuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.InClassQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.SchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class QuestionFragment extends Fragment {
    public final static String TAG = QuestionFragment.class.getName();

    private QuestionViewModel mViewModel;

    private TextView textQuestion;
    private TextView textQuestionNumber;

    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonAnswer4;

    private Button buttonEndGame;

    private CardView answerColor;

    private Question currentQuestion;
    private int questionNumber;

    private final IFragmentNavigation mFragmentNavigation;
    private final ISchedulersProvider mSchedulersProvider;

    private boolean checkAnswer;

    public static Fragment newInstance(IFragmentNavigation fragmentNavigation, ISchedulersProvider schedulersProvider) {
        return new QuestionFragment(fragmentNavigation, schedulersProvider);
    }

    public QuestionFragment(IFragmentNavigation fragmentNavigation, ISchedulersProvider schedulersProvider) {
        mFragmentNavigation = fragmentNavigation;
        mSchedulersProvider = schedulersProvider;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textQuestion = view.findViewById(R.id.question_text);
        textQuestionNumber = view.findViewById(R.id.question_number_text);

        buttonAnswer1 = view.findViewById(R.id.answer1);
        buttonAnswer2 = view.findViewById(R.id.answer2);
        buttonAnswer3 = view.findViewById(R.id.answer3);
        buttonAnswer4 = view.findViewById(R.id.answer4);

        buttonEndGame = view.findViewById(R.id.end_game_button);

        answerColor = view.findViewById(R.id.answer_color);

        buttonAnswer1.setOnClickListener(viewButton -> onClickAnswer(0));
        buttonAnswer2.setOnClickListener(viewButton -> onClickAnswer(1));
        buttonAnswer3.setOnClickListener(viewButton -> onClickAnswer(2));
        buttonAnswer4.setOnClickListener(viewButton -> onClickAnswer(3));

        buttonEndGame.setOnClickListener(viewButton ->
                mFragmentNavigation.replace(SelectTypeGameFragment.newInstance(mFragmentNavigation, mSchedulersProvider), SelectTypeGameFragment.TAG));

        createViewModel();
        observeLiveData();

        questionNumber = 0;
        checkAnswer = false;

        nextQuestion();
    }

    private void onClickAnswer(int indexAnswer) {
        if (checkAnswer) {
            return;
        }

        checkAnswer = true;

        if (currentQuestion.getCorrectAnswerIndex() == indexAnswer) {
            answerColor.setCardBackgroundColor(getResources().getColor(R.color.green));
        } else {
            answerColor.setCardBackgroundColor(getResources().getColor(R.color.red));
        }

        Single.just("")
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .delay(250, TimeUnit.MILLISECONDS)
                .doOnSuccess(s -> {
                    answerColor.setCardBackgroundColor(getResources().getColor(R.color.white));
                    nextQuestion();

                    checkAnswer = false;
                })
                .subscribe();
    }

    private void nextQuestion() {
        mViewModel.getQuestion();
    }

    private void createViewModel() {

        InClassDataBase dataBase = new InClassDataBase();
        IDataBaseApi dataBaseApi = new InClassDataBaseApi(dataBase);
        IQuestionRepository repository = new InClassQuestionRepository(dataBaseApi);
        IQuestionInteractor interactor = new InClassQuestionInteractor(repository);
        ISchedulersProvider schedulersProvider = new SchedulersProvider();

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new QuestionViewModel(interactor, schedulersProvider);
            }
        }).get(QuestionViewModel.class);
    }

    private void observeLiveData() {
        mViewModel.getMyLiveData().observe(getViewLifecycleOwner(), this::showData);
    }

    private void showData(@NonNull Question question) {
        questionNumber++;
        textQuestionNumber.setText(String.valueOf(questionNumber));
        textQuestion.setText(question.getQuestionText());

        buttonAnswer1.setText(question.getAnswers().get(0));
        buttonAnswer2.setText(question.getAnswers().get(1));
        buttonAnswer3.setText(question.getAnswers().get(2));
        buttonAnswer4.setText(question.getAnswers().get(3));

        currentQuestion = question;
    }
}
