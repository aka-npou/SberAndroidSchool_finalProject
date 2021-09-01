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
import com.aka_npou.sberandroidschool_finalproject.data.converter.QuestionConverter;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.IQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase.InClassDataBase;
import com.aka_npou.sberandroidschool_finalproject.data.dataSource.inClassDataBase.InClassQuestionDao;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.data.repository.QuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IQuestionInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.interactor.IStatisticInteractor;
import com.aka_npou.sberandroidschool_finalproject.domain.repository.IQuestionRepository;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.ISchedulersProvider;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class QuestionFragment extends Fragment {
    public final static String TAG = QuestionFragment.class.getSimpleName();

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

    private final IQuestionInteractor mQuestionInteractor;
    private final IStatisticInteractor mStatisticInteractor;
    private final QuestionConverter mQuestionConverter;

    public static Fragment newInstance(IFragmentNavigation fragmentNavigation,
                                       ISchedulersProvider schedulersProvider,
                                       IQuestionInteractor questionInteractor,
                                       IStatisticInteractor statisticInteractor,
                                       QuestionConverter questionConverter) {
        return new QuestionFragment(fragmentNavigation, schedulersProvider, questionInteractor, statisticInteractor, questionConverter);
    }

    public QuestionFragment(IFragmentNavigation fragmentNavigation,
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
                mFragmentNavigation.replace(
                        SelectTypeGameFragment.newInstance(mFragmentNavigation,
                                mSchedulersProvider,
                                mQuestionInteractor,
                                mStatisticInteractor,
                                mQuestionConverter),
                        SelectTypeGameFragment.TAG,
                        false));

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

        mViewModel.addAnswerResult(currentQuestion.getId(),
                 indexAnswer,
                currentQuestion.getCorrectAnswerIndex() == indexAnswer,
                 new Date());

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
        IQuestionDao dataBaseApi = new InClassQuestionDao(dataBase);
        IQuestionRepository repository = new QuestionRepository(dataBaseApi, mQuestionConverter);

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new QuestionViewModel(mQuestionInteractor, mStatisticInteractor, mSchedulersProvider);
            }
        }).get(QuestionViewModel.class);
    }

    private void observeLiveData() {
        mViewModel.getQuestionLiveData().observe(getViewLifecycleOwner(), this::showData);
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
