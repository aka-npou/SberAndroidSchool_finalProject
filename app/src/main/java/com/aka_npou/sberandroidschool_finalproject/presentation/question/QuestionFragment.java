package com.aka_npou.sberandroidschool_finalproject.presentation.question;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aka_npou.sberandroidschool_finalproject.QuizApplication;
import com.aka_npou.sberandroidschool_finalproject.R;
import com.aka_npou.sberandroidschool_finalproject.di.activity.ActivityComponent;
import com.aka_npou.sberandroidschool_finalproject.domain.model.Question;
import com.aka_npou.sberandroidschool_finalproject.presentation.common.IFragmentNavigation;
import com.aka_npou.sberandroidschool_finalproject.presentation.selectTypeGame.SelectTypeGameFragment;

import java.util.Calendar;
import java.util.Date;

public class QuestionFragment extends Fragment {
    public final static String TAG = QuestionFragment.class.getSimpleName();

    private QuestionViewModel viewModel;

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

    private boolean checkAnswer;

    public static Fragment newInstance(IFragmentNavigation fragmentNavigation) {
        return new QuestionFragment(fragmentNavigation);
    }

    public QuestionFragment(IFragmentNavigation fragmentNavigation) {
        mFragmentNavigation = fragmentNavigation;
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
                mFragmentNavigation.replace(SelectTypeGameFragment.TAG, false));

        createViewModel();
        observeLiveData();

        questionNumber = 0;
        checkAnswer = false;

        nextQuestion(0);
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

        Date startCurrentDate = getStartCurrentDate();
        Log.i(TAG, "onClickAnswer: " + startCurrentDate);
        viewModel.addAnswerResult(currentQuestion.getId(),
                 indexAnswer,
                currentQuestion.getCorrectAnswerIndex() == indexAnswer,
                startCurrentDate);

        nextQuestion(250);
    }

    private void nextQuestion(long time) {
        viewModel.getQuestion(time);
    }

    private void createViewModel() {
        //null может быть если делать до onAttached, а мы делаем в onViewCreated, что после
        ActivityComponent activityComponent = QuizApplication.getAppComponent(getActivity()).getActivityComponent();
        viewModel = new ViewModelProvider(this, activityComponent.getViewModelFactory()).get(QuestionViewModel.class);
    }

    private void observeLiveData() {
        viewModel.getQuestionLiveData().observe(getViewLifecycleOwner(), this::showData);
    }

    private void showData(@NonNull Question question) {
        answerColor.setCardBackgroundColor(getResources().getColor(R.color.white));
        checkAnswer = false;

        questionNumber++;
        textQuestionNumber.setText(String.valueOf(questionNumber));
        textQuestion.setText(question.getQuestionText());

        buttonAnswer1.setText(question.getAnswers().get(0));
        buttonAnswer2.setText(question.getAnswers().get(1));
        buttonAnswer3.setText(question.getAnswers().get(2));
        buttonAnswer4.setText(question.getAnswers().get(3));

        currentQuestion = question;
    }

    private Date getStartCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                0,
                0,
                0);

        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
